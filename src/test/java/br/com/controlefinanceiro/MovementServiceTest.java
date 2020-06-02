package br.com.controlefinanceiro;

import br.com.controlefinanceiro.domain.movement.MovementEntity;
import br.com.controlefinanceiro.domain.movement.MovementRepository;
import br.com.controlefinanceiro.domain.movement.MovementService;
import br.com.controlefinanceiro.domain.operation.OperationEntity;
import br.com.controlefinanceiro.domain.operation.OperationType;
import br.com.controlefinanceiro.domain.operation.PaymentRecurrentType;
import br.com.controlefinanceiro.domain.operation.PaymentRecurrentVO;
import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MovementServiceTest {

    @InjectMocks
    private MovementService movementService;

    @Mock
    private MovementRepository repository;

    @Test
    public void createMovementsRecurrentOutflowTest() {

        PaymentRecurrentVO paymentRecurrentVO = PaymentRecurrentVO.builder()
                .days(Arrays.asList(10))
                .dateStart(LocalDate.of(2020, 1, 10))
                .dateEnd(LocalDate.of(2020, 3, 10))
                .type(PaymentRecurrentType.MONTHLY).build();

        movementService.createMovementsRecurrent(
                OperationEntity.builder().id(11l).operationType(OperationType.OUTFLOW).build(),
                BigDecimal.valueOf(1000),
                WalletEntity.builder().id(12l).build(),
                paymentRecurrentVO);

        ArgumentCaptor<List<MovementEntity>> argument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(repository).saveAll(argument.capture());

        List<MovementEntity> movementEntityList = argument.getValue();

        Assertions.assertNotNull(movementEntityList);
        Assertions.assertEquals(3, movementEntityList.size());
        Assertions.assertEquals(LocalDate.of(2020, 1, 10), movementEntityList.get(0).getDateDue());
        Assertions.assertEquals(LocalDate.of(2020, 2, 10), movementEntityList.get(1).getDateDue());
        Assertions.assertEquals(LocalDate.of(2020, 3, 10), movementEntityList.get(2).getDateDue());
        Assertions.assertEquals(0, BigDecimal.valueOf(-1000).compareTo(movementEntityList.get(0).getValuePayment()));
        Assertions.assertEquals(0, BigDecimal.valueOf(-1000).compareTo(movementEntityList.get(1).getValuePayment()));
        Assertions.assertEquals(0, BigDecimal.valueOf(-1000).compareTo(movementEntityList.get(2).getValuePayment()));
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(0).getStatus());
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(1).getStatus());
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(2).getStatus());
        Assertions.assertEquals(11, movementEntityList.get(0).getOperation().getId());
        Assertions.assertEquals(11, movementEntityList.get(1).getOperation().getId());
        Assertions.assertEquals(11, movementEntityList.get(2).getOperation().getId());
        Assertions.assertEquals(12, movementEntityList.get(0).getWalletEntity().getId());
        Assertions.assertEquals(12, movementEntityList.get(1).getWalletEntity().getId());
        Assertions.assertEquals(12, movementEntityList.get(2).getWalletEntity().getId());
    }

    @Test
    public void createMovementsRecurrentDepositTest() {

        PaymentRecurrentVO paymentRecurrentVO = PaymentRecurrentVO.builder()
                .days(Arrays.asList(10))
                .dateStart(LocalDate.of(2020, 1, 10))
                .dateEnd(LocalDate.of(2020, 3, 10))
                .type(PaymentRecurrentType.MONTHLY).build();

        movementService.createMovementsRecurrent(
                OperationEntity.builder().id(11l).operationType(OperationType.DEPOSIT).build(),
                BigDecimal.valueOf(1000),
                WalletEntity.builder().id(12l).build(),
                paymentRecurrentVO);

        ArgumentCaptor<List<MovementEntity>> argument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(repository).saveAll(argument.capture());

        List<MovementEntity> movementEntityList = argument.getValue();

        Assertions.assertNotNull(movementEntityList);
        Assertions.assertEquals(3, movementEntityList.size());
        Assertions.assertEquals(LocalDate.of(2020, 1, 10), movementEntityList.get(0).getDateDue());
        Assertions.assertEquals(LocalDate.of(2020, 2, 10), movementEntityList.get(1).getDateDue());
        Assertions.assertEquals(LocalDate.of(2020, 3, 10), movementEntityList.get(2).getDateDue());
        Assertions.assertEquals(0, BigDecimal.valueOf(1000).compareTo(movementEntityList.get(0).getValuePayment()));
        Assertions.assertEquals(0, BigDecimal.valueOf(1000).compareTo(movementEntityList.get(1).getValuePayment()));
        Assertions.assertEquals(0, BigDecimal.valueOf(1000).compareTo(movementEntityList.get(2).getValuePayment()));
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(0).getStatus());
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(1).getStatus());
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(2).getStatus());
        Assertions.assertEquals(11, movementEntityList.get(0).getOperation().getId());
        Assertions.assertEquals(11, movementEntityList.get(1).getOperation().getId());
        Assertions.assertEquals(11, movementEntityList.get(2).getOperation().getId());
        Assertions.assertEquals(12, movementEntityList.get(0).getWalletEntity().getId());
        Assertions.assertEquals(12, movementEntityList.get(1).getWalletEntity().getId());
        Assertions.assertEquals(12, movementEntityList.get(2).getWalletEntity().getId());
    }

    @Test
    public void createMovementsInstallmentsOutflowTest() {

        movementService.createMovementsInstallments(
                OperationEntity.builder().id(11l).operationType(OperationType.OUTFLOW).build(),
                LocalDate.of(2020, 1, 1),
                3,
                BigDecimal.valueOf(900),
                WalletEntity.builder().id(12l).build());

        ArgumentCaptor<List<MovementEntity>> argument = ArgumentCaptor.forClass(List.class);
        Mockito.verify(repository).saveAll(argument.capture());

        List<MovementEntity> movementEntityList = argument.getValue();

        Assertions.assertNotNull(movementEntityList);
        Assertions.assertEquals(3, movementEntityList.size());
        Assertions.assertEquals(LocalDate.of(2020, 2, 1), movementEntityList.get(0).getDateDue());
        Assertions.assertEquals(LocalDate.of(2020, 3, 1), movementEntityList.get(1).getDateDue());
        Assertions.assertEquals(LocalDate.of(2020, 4, 1), movementEntityList.get(2).getDateDue());
        Assertions.assertEquals(0, BigDecimal.valueOf(-300).compareTo(movementEntityList.get(0).getValuePayment()));
        Assertions.assertEquals(0, BigDecimal.valueOf(-300).compareTo(movementEntityList.get(1).getValuePayment()));
        Assertions.assertEquals(0, BigDecimal.valueOf(-300).compareTo(movementEntityList.get(2).getValuePayment()));
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(0).getStatus());
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(1).getStatus());
        Assertions.assertEquals(StatusPaymentType.PENDING, movementEntityList.get(2).getStatus());
        Assertions.assertEquals(11, movementEntityList.get(0).getOperation().getId());
        Assertions.assertEquals(11, movementEntityList.get(1).getOperation().getId());
        Assertions.assertEquals(11, movementEntityList.get(2).getOperation().getId());
        Assertions.assertEquals(12, movementEntityList.get(0).getWalletEntity().getId());
        Assertions.assertEquals(12, movementEntityList.get(1).getWalletEntity().getId());
        Assertions.assertEquals(12, movementEntityList.get(2).getWalletEntity().getId());
    }

}
