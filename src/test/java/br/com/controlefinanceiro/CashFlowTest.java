package br.com.controlefinanceiro;

import br.com.controlefinanceiro.domain.cashflow.CashFlowService;
import br.com.controlefinanceiro.domain.cashflow.CashFlowVO;
import br.com.controlefinanceiro.domain.movement.MovementEntity;
import br.com.controlefinanceiro.domain.movement.MovementService;
import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CashFlowTest {

    @InjectMocks
    private CashFlowService cashFlowService;

    @Mock
    private MovementService movementService;

    @Test
    void cashFlowTest(){

        when(movementService.findAll()).thenReturn(mockMovementEntities());

        Collection<CashFlowVO> cashFlow = cashFlowService.cashFlow(null, null);

        cashFlow.stream().forEach(cf -> System.out.println(cf.getDate() + " - " + cf.getBalance()));

    }

    List<MovementEntity> mockMovementEntities(){

        List<MovementEntity> movementEntities = Arrays.asList(
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 04, 01))
                        .datePayment(LocalDate.of(2020, 02, 01))
                        .valuePayment(BigDecimal.valueOf(1000))
                        .status(StatusPaymentType.PAID_OUT)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 04, 02))
                        .datePayment(LocalDate.of(2020, 02, 04))
                        .valuePayment(BigDecimal.valueOf(100).negate())
                        .status(StatusPaymentType.PAID_OUT)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 04, 05))
                        .datePayment(LocalDate.of(2020, 04, 05))
                        .valuePayment(BigDecimal.valueOf(260))
                        .status(StatusPaymentType.LATE)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 04, 06))
                        .valuePayment(BigDecimal.valueOf(50).negate())
                        .status(StatusPaymentType.LATE)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 04, 10))
                        .valuePayment(BigDecimal.valueOf(30).negate())
                        .status(StatusPaymentType.PENDING)
                        .build()
        );

        return movementEntities;

    }



}
