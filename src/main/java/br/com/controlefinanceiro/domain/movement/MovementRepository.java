package br.com.controlefinanceiro.domain.movement;

import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
interface MovementRepository extends JpaRepository<MovementEntity, Long> {

    List<MovementEntity> findByStatusAndDatePaymentGreaterThanEqualAndDatePaymentLessThanEqualOrderByDatePayment
            (StatusPaymentType status, LocalDate datePaymentStart, LocalDate datePaymentEnd);

    @Query("select sum(m.valuePayment) from MovementEntity m where m.datePayment <= :datePayment")
    BigDecimal sumValuePaymentByDatePaymentLessThanEqual(@Param("datePayment") LocalDate datePayment);

}
