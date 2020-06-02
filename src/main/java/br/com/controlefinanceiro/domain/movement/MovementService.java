package br.com.controlefinanceiro.domain.movement;

import br.com.controlefinanceiro.domain.creditCard.CreditCardService;
import br.com.controlefinanceiro.domain.creditCard.invoice.InvoiceEntity;
import br.com.controlefinanceiro.domain.operation.OperationEntity;
import br.com.controlefinanceiro.domain.operation.OperationType;
import br.com.controlefinanceiro.domain.operation.PaymentRecurrentType;
import br.com.controlefinanceiro.domain.operation.PaymentRecurrentVO;
import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodType;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MovementService {

    private MovementRepository repository;

    private CreditCardService creditCardService;

    @Autowired
    public MovementService(MovementRepository repository, CreditCardService creditCardService){
        this.repository = repository;
        this.creditCardService = creditCardService;
    }

    public List<MovementEntity> findAll(){
        return repository.findAll();
    }

    public BigDecimal sumValuePaymentByDatePaymentLessThanEqual(LocalDate date) {
        return repository.sumValuePaymentByDatePaymentLessThanEqual(date);
    }

    public void createMovementPaymentInCash(OperationEntity operationEntity, BigDecimal value, WalletEntity walletEntity){
        if(operationEntity.isOperationOutFlow()){
            value = value.negate();
        }
        repository.save(MovementEntity.builder()
                .operation(operationEntity)
                .valuePayment(value)
                .status(StatusPaymentType.PAID_OUT)
                .dateDue(operationEntity.getDateBuy())
                .datePayment(operationEntity.getDateBuy())
                .walletEntity(walletEntity)
                .build());
    }

    public void createMovementsInstallments(OperationEntity operationEntity, LocalDate dateBuy, Integer numberInstallments, BigDecimal totalValue, WalletEntity walletEntity) {

        List<MovementEntity> movementEntities = new ArrayList<>();

        BigDecimal valueInstallment = totalValue.divide(BigDecimal.valueOf(numberInstallments), 2, RoundingMode.DOWN);
        BigDecimal valueMissing = totalValue.subtract(valueInstallment.multiply(BigDecimal.valueOf(numberInstallments)));

        if(OperationType.OUTFLOW == operationEntity.getOperationType()){
            valueInstallment = valueInstallment.negate();
            valueMissing = valueMissing.negate();
        }

        LocalDate dateDue = null;

        if(PaymentMethodType.CREDIT_CARD == operationEntity.getPaymentMethodType()){
            dateDue = creditCardService.getDateDue(operationEntity.getDateBuy(), operationEntity.getPaymentMethod());
        } else {
            dateDue = dateBuy.plusMonths(1);
        }

        for(int x = 0; x < numberInstallments; x++){
            MovementEntity movementEntity =
                    MovementEntity.builder()
                            .operation(operationEntity)
                            .valuePayment(valueInstallment)
                            .status(StatusPaymentType.PENDING)
                            .dateDue(dateDue.plusMonths(x))
                            .walletEntity(walletEntity)
                            .build();

            if(PaymentMethodType.CREDIT_CARD == operationEntity.getPaymentMethodType()){
                InvoiceEntity invoiceEntity = creditCardService.getInvoice(dateDue, operationEntity.getPaymentMethod().getId());
                movementEntity.setInvoiceEntity(invoiceEntity);
            }
            if(x == numberInstallments-1){
                movementEntity.sumValuePayment(valueMissing);
            }
            movementEntities.add(movementEntity);
        }

        repository.saveAll(movementEntities);
    }

    @Transactional
    public void createMovementsRecurrent(OperationEntity operationEntity, BigDecimal value, WalletEntity walletEntity, PaymentRecurrentVO paymentRecurrentVO) {

        if(operationEntity.isOperationOutFlow()){
            value = value.negate();
        }

        final BigDecimal valuePayment = value;

        List<LocalDate> datesDue = getDatesDue(paymentRecurrentVO);

        List<MovementEntity> movementEntities = new ArrayList<>();

        datesDue.forEach(dateDue -> movementEntities.add(MovementEntity.builder()
                .operation(operationEntity)
                .valuePayment(valuePayment)
                .status(StatusPaymentType.PENDING)
                .dateDue(dateDue)
                .walletEntity(walletEntity)
                .build()));

        repository.saveAll(movementEntities);

    }

    public List<LocalDate> getDatesDue(PaymentRecurrentVO paymentRecurrentVO) {

        List<LocalDate> datesDue = new ArrayList<>();

        PaymentRecurrentType paymentRecurrentType = paymentRecurrentVO.getType();
        List<Integer> days = paymentRecurrentVO.getDays();
        LocalDate dateStart = paymentRecurrentVO.getDateStart();
        LocalDate dateEnd = paymentRecurrentVO.getDateEnd();

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

}
