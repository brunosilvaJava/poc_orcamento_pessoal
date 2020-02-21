package br.com.controlefinanceiro.domain.operation;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class OperationService {

    private OperationRepository operationRepository;

    private InstallmentOperationRepository installmentOperationRepository;

    private PaymentMethodService paymentMethodService;

    @Autowired
    public OperationService(OperationRepository operationRepository, InstallmentOperationRepository installmentOperationRepository, PaymentMethodService paymentMethodService) {
        this.operationRepository = operationRepository;
        this.installmentOperationRepository = installmentOperationRepository;
        this.paymentMethodService = paymentMethodService;
    }

    @Transactional
    public void save(OperationVO operationVO){

        OperationEntity operationEntity = OperationEntity.builder()
                .description(operationVO.getDescription())
                .operationType(operationVO.getOperationType())
                .dateHourBuy(operationVO.getDateHourBuy())
                .paymentType(operationVO.getPaymentType())
                .paymentMethodEntity(paymentMethodService.findById2(operationVO.getIdPaymentMethod()))
                .build();

        operationRepository.save(operationEntity);

        switch (operationVO.getPaymentType()){
            case IN_CASH:
                saveBuyPaymentInCash(operationEntity, operationVO.getTotalValue());
                break;
            case INSTALLMENT:
                saveBuyPaymentInstallments(operationEntity, operationVO);
                break;
        }

    }

    private void saveBuyPaymentInCash(OperationEntity operationEntity, BigDecimal value){
        installmentOperationRepository.save(InstallmentOperationEntity.builder()
                .operation(operationEntity)
                .valuePayment(value)
                .valuePrevision(value)
                .datePrevisionPayment(operationEntity.getDateHourBuy())
                .dateHourPayment(operationEntity.getDateHourBuy())
                .build());
    }

    private void saveBuyPaymentInstallments(OperationEntity operationEntity, OperationVO operationVO){

        int numberInstallments = operationVO.getNumberInstallments();

        BigDecimal totalValue = operationVO.getTotalValue();
        BigDecimal valueInstallment = totalValue.divide(BigDecimal.valueOf(numberInstallments), 2, RoundingMode.DOWN);

        BigDecimal valueMissing = totalValue.subtract(valueInstallment.multiply(BigDecimal.valueOf(numberInstallments)));

        for(int x = 1; x <= numberInstallments; x++){

            InstallmentOperationEntity installmentOperationEntity =
                    InstallmentOperationEntity.builder()
                            .operation(operationEntity)
                            .valuePayment(BigDecimal.ZERO)
                            .valuePrevision(valueInstallment)
                            .datePrevisionPayment(operationEntity.getDateHourBuy().plusMonths(x))
                            .build();

            if(x == numberInstallments){
                installmentOperationEntity.getValuePayment().add(valueMissing);
            }

            installmentOperationRepository.save(installmentOperationEntity);
        }
    }

}