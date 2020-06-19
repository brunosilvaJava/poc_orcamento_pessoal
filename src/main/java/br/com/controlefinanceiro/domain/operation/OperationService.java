package br.com.controlefinanceiro.domain.operation;

import br.com.controlefinanceiro.domain.movement.MovementService;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodService;
import br.com.controlefinanceiro.domain.recurrent.RecurrentService;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import br.com.controlefinanceiro.domain.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class OperationService {

    private OperationRepository operationRepository;
    private MovementService movementService;
    private PaymentMethodService paymentMethodService;
    private WalletService walletService;

    @Autowired
    public OperationService(OperationRepository operationRepository, MovementService movementService,
                            PaymentMethodService paymentMethodService, WalletService walletService) {
        this.operationRepository = operationRepository;
        this.movementService = movementService;
        this.paymentMethodService = paymentMethodService;
        this.walletService = walletService;
    }

    @Transactional
    public void create(OperationVO operationVO){

        OperationEntity operationEntity = OperationEntity.builder()
                .description(operationVO.getDescription())
                .operationType(operationVO.getOperationType())
                .dateBuy(operationVO.getDateBuy() != null ? operationVO.getDateBuy() : LocalDate.now())
                .paymentType(operationVO.getPaymentType())
                .paymentMethod(paymentMethodService.findEntityById(operationVO.getIdPaymentMethod()))
                .build();

        operationRepository.save(operationEntity);

        WalletEntity walletEntity = null;

        if(operationVO.getIdWallet() != null){
            walletEntity = walletService.findById(operationVO.getIdWallet());
        }
        else {
            walletEntity = operationEntity.getPaymentMethod().getWalletEntity();
        }

        switch (operationVO.getPaymentType()){
            case IN_CASH:
                movementService.createMovementPaymentInCash(operationEntity, operationVO.getValue(), walletEntity);
                break;
            case INSTALLMENT:
                movementService.createMovementsInstallments(operationEntity, operationVO.getDateBuy(), operationVO.getNumberInstallments(), operationVO.getValue(), walletEntity);
                break;
        }
    }

}