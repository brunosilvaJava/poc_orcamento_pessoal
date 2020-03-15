package br.com.controlefinanceiro.domain.operation;

import br.com.controlefinanceiro.domain.creditCard.CreditCardEntity;
import br.com.controlefinanceiro.domain.creditCard.CreditCardService;
import br.com.controlefinanceiro.domain.movement.MovementEntity;
import br.com.controlefinanceiro.domain.movement.MovementService;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodService;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodType;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import br.com.controlefinanceiro.domain.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class OperationService {

    private OperationRepository operationRepository;
    private MovementService movementService;
    private PaymentMethodService paymentMethodService;
    private CreditCardService creditCardService;
    private WalletService walletService;

    @Autowired
    public OperationService(OperationRepository operationRepository, MovementService movementService,
                            PaymentMethodService paymentMethodService, CreditCardService creditCardService,
                            WalletService walletService) {
        this.operationRepository = operationRepository;
        this.movementService = movementService;
        this.paymentMethodService = paymentMethodService;
        this.creditCardService = creditCardService;
        this.walletService = walletService;
    }

    @Transactional
    public void create(OperationVO operationVO){

        OperationEntity operationEntity = OperationEntity.builder()
                .description(operationVO.getDescription())
                .operationType(operationVO.getOperationType())
                .dateBuy(operationVO.getDateBuy() != null ? operationVO.getDateBuy() : LocalDate.now())
                .paymentType(operationVO.getPaymentType())
                .paymentMethodEntity(paymentMethodService.findEntityById(operationVO.getIdPaymentMethod()))
                .build();

        operationRepository.save(operationEntity);

        WalletEntity walletEntity = null;

        if(operationVO.getIdWallet() != null){
            walletEntity = walletService.findById(operationVO.getIdWallet());
        }
        else {
            walletEntity = operationEntity.getPaymentMethodEntity().getWallet();
        }

        switch (operationVO.getPaymentType()){
            case IN_CASH:
                saveoOperationPaymentInCash(operationEntity, operationVO.getTotalValue(), walletEntity);
                break;
            case INSTALLMENT:
                saveOperationPaymentInstallments(operationEntity, operationVO, walletEntity);
                break;
            case RECURRENT:
                saveOperationRecurrent(operationEntity, operationVO, walletEntity);
                break;
        }
    }

    private void saveOperationRecurrent(OperationEntity operationEntity, OperationVO operationVO, WalletEntity walletEntity) {

        BigDecimal value = operationVO.getTotalValue();

        if(isOperationOutFlow(operationEntity)){
            value = value.negate();
        }

        final BigDecimal valuePayment = value;

        List<LocalDate> datesDue = getDatesDue(operationVO);

        datesDue.forEach(dateDue -> movementService.create(MovementEntity.builder()
                .operation(operationEntity)
                .valuePayment(valuePayment)
                .status(StatusPaymentType.PENDING)
                .dateDue(dateDue)
                .wallet(walletEntity)
                .build()));

    }

    private List<Integer> getFrequencyForWeek(List<Integer> daysWeek){
        final List<Integer> frequency = new ArrayList<>();
        if(daysWeek.size() == 7){
            frequency.add(1);
            return frequency;
        }
        for(int x=0;x<daysWeek.size(); x++){
            if(x < daysWeek.size()-1){
                frequency.add(daysWeek.get(x+1)-daysWeek.get(x));
            } else {
                frequency.add(7-daysWeek.get(x)+daysWeek.get(0));
            }
        }
        return frequency;
    }

    public List<LocalDate> getDatesDue(OperationVO operationVO) {

        List<LocalDate> datesDue = new ArrayList<>();

        PaymentRecurrentType paymentRecurrentType = operationVO.getPaymentRecurrent().getType();
        List<Integer> days = operationVO.getPaymentRecurrent().getDays();
        LocalDate dateStart = operationVO.getPaymentRecurrent().getDateStart();
        LocalDate dateEnd = operationVO.getPaymentRecurrent().getDateEnd();

        LocalDate dateDue = LocalDate.from(dateStart);

        boolean isDatesConcluded = false;

        switch (paymentRecurrentType){

            case WEEKLY:
                int dayWeek = dateDue.getDayOfWeek().getValue()+1;
                if(dayWeek <= days.get(0)){
                    dateDue = dateDue.plusDays(days.get(0)-dayWeek);
                } else {
                    dateDue = dateDue.plusDays(7-dayWeek+days.get(0));
                }
                List<Integer> frequency = getFrequencyForWeek(days);

                datesDue.add(dateDue);

                while(dateDue.isBefore(dateEnd)){
                    for (Integer f : frequency) {
                        dateDue = dateDue.plusDays(f);
                        if(!dateDue.isBefore(dateEnd)){break;}
                        datesDue.add(dateDue);
                    }
                }
                break;

            case BIWEEKLY:

                dateDue = LocalDate.from(dateStart).withDayOfMonth(days.get(0));
                if(dateStart.isAfter(dateDue)){
                    dateDue = dateDue.plusMonths(1);
                }

                LocalDate dateDue2 = LocalDate.from(dateStart).withDayOfMonth(days.get(1));
                if(dateStart.isAfter(dateDue2)){
                    dateDue = dateDue.plusMonths(1);
                }

                isDatesConcluded = false;

                while(!isDatesConcluded) {

                    if(dateDue.isBefore(dateEnd)){
                        datesDue.add(dateDue);
                        dateDue = dateDue.plusMonths(1);
                    }

                    if(dateDue2.isBefore(dateEnd)){
                        datesDue.add(dateDue2);
                        dateDue2 = dateDue2.plusMonths(1);
                    }
                    else{
                        isDatesConcluded = true;
                    }

                }

                break;

            case MONTHLY:

                dateDue = LocalDate.from(dateStart).withDayOfMonth(days.get(0));

                if(dateStart.isAfter(dateDue)){
                    dateDue = dateDue.plusMonths(1);
                }

                while(!dateDue.isAfter(dateEnd)) {
                    datesDue.add(dateDue);
                    dateDue = dateDue.plusMonths(1);
                }

                break;

            case YEARLY:
                break;

        }

        datesDue.sort(Comparator.naturalOrder());

        return datesDue;

    }

    private void saveoOperationPaymentInCash(OperationEntity operationEntity, BigDecimal value, WalletEntity walletEntity){

        if(isOperationOutFlow(operationEntity)){
            value = value.negate();
        }

        movementService.create(MovementEntity.builder()
                .operation(operationEntity)
                .valuePayment(value)
                .status(StatusPaymentType.PENDING)
                .dateDue(operationEntity.getDateBuy())
                .datePayment(operationEntity.getDateBuy())
                .wallet(walletEntity)
                .build());
    }

    private void saveOperationPaymentInstallments(OperationEntity operationEntity, OperationVO operationVO, WalletEntity walletEntity){

        int numberInstallments = operationVO.getNumberInstallments();

        BigDecimal totalValue = operationVO.getTotalValue();
        BigDecimal valueInstallment = totalValue.divide(BigDecimal.valueOf(numberInstallments), 2, RoundingMode.DOWN);
        BigDecimal valueMissing = totalValue.subtract(valueInstallment.multiply(BigDecimal.valueOf(numberInstallments)));

        if(isOperationOutFlow(operationEntity)){
            valueInstallment = valueInstallment.negate();
            valueMissing = valueMissing.negate();
        }

        LocalDate dateDue = null;

        if(PaymentMethodType.CREDIT_CARD == operationEntity.getPaymentMethodType()){
            CreditCardEntity creditCardEntity = creditCardService.findByPaymentMethod(operationEntity.getPaymentMethodEntity());
            dateDue = creditCardService.getDateDue(operationEntity.getDateBuy(), creditCardEntity);
        } else {
            dateDue = operationVO.getDateBuy().plusMonths(1);
        }

        for(int x = 0; x < numberInstallments; x++){

            MovementEntity movementEntity =
                    MovementEntity.builder()
                            .operation(operationEntity)
                            .valuePayment(valueInstallment)
                            .status(StatusPaymentType.PENDING)
                            .dateDue(dateDue.plusMonths(x))
                            .wallet(walletEntity)
                            .build();

            if(x == numberInstallments-1){
                movementEntity.sumValuePayment(valueMissing);
            }

            movementService.create(movementEntity);
        }
    }

    public boolean isOperationOutFlow(OperationEntity operationEntity){
        return OperationType.OUTFLOW == operationEntity.getOperationType();
    }

}