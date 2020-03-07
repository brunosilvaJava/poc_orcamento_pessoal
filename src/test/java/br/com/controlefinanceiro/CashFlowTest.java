package br.com.controlefinanceiro;

import br.com.controlefinanceiro.domain.movement.MovementEntity;
import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CashFlowTest {

    List<MovementEntity> mockMovementEntities(){

        List<MovementEntity> movementEntities = Arrays.asList(
                MovementEntity.builder()
                        .dateDue(LocalDate.now().minusMonths(1))
                        .valuePayment(BigDecimal.TEN.negate())
                        .status(StatusPaymentType.PAID_OUT)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.now().minusMonths(2))
                        .valuePayment(BigDecimal.TEN.negate())
                        .status(StatusPaymentType.PAID_OUT)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.now())
                        .valuePayment(BigDecimal.TEN.negate())
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 02, 01))
                        .valuePayment(BigDecimal.valueOf(1000))
                        .datePayment(LocalDate.of(2020, 02, 01))
                        .status(StatusPaymentType.LATE)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 03, 01))
                        .valuePayment(BigDecimal.valueOf(1000))
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 04, 01))
                        .valuePayment(BigDecimal.valueOf(1000))
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 03, 05))
                        .valuePayment(BigDecimal.valueOf(700).negate())
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 04, 05))
                        .valuePayment(BigDecimal.valueOf(700).negate())
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 05, 05))
                        .valuePayment(BigDecimal.valueOf(700).negate())
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 03, 05))
                        .valuePayment(BigDecimal.valueOf(250))
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 04, 05))
                        .valuePayment(BigDecimal.valueOf(250))
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 05, 05))
                        .valuePayment(BigDecimal.valueOf(250))
                        .status(StatusPaymentType.PENDING)
                        .build(),
                MovementEntity.builder()
                        .dateDue(LocalDate.of(2020, 01, 01))
                        .datePayment(LocalDate.of(2020, 01, 01))
                        .valuePayment(BigDecimal.valueOf(1000))
                        .status(StatusPaymentType.LATE)
                        .build()
        );

        return movementEntities;

    }



}
