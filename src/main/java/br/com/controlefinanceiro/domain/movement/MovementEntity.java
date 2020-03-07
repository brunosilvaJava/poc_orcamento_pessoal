package br.com.controlefinanceiro.domain.movement;

import br.com.controlefinanceiro.domain.operation.OperationEntity;
import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "movement")
public class MovementEntity implements Comparable<MovementEntity>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movement")
    private Long id;

    @Column(name = "date_payment")
    private LocalDate datePayment;

    @Column(name = "date_due", nullable = false)
    private LocalDate dateDue;

    @Column(name = "value_payment", nullable = false)
    private BigDecimal valuePayment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_payment_type", nullable = false)
    private StatusPaymentType status;

    @ManyToOne
    @JoinColumn(name = "id_operation", nullable = false)
    private OperationEntity operation;

    @ManyToOne
    @JoinColumn(name = "id_wallet")
    private WalletEntity wallet;

    public boolean isDeposit(){
        return valuePayment.signum() > 0;
    }

    public void sumValuePayment(BigDecimal value){
        setValuePayment(getValuePayment().add(value));
    }

    @Override
    public int compareTo(MovementEntity movementEntity) {
        switch (movementEntity.getStatus()){
            case PENDING:
                return dateDue.compareTo(movementEntity.getDateDue());
            case LATE:
                if(status == StatusPaymentType.LATE){
                    return datePayment.compareTo(movementEntity.getDatePayment());
                }
                return dateDue.compareTo(movementEntity.getDatePayment());
            case PAID_OUT:
                return 1;
        }
        return 1;
    }

    public LocalDate getDatePrevisionPaymentByStatus(){
        switch (getStatus()){
            case PENDING:
                return getDateDue();
            case LATE:
                return getDatePayment();
            case PAID_OUT:
                return LocalDate.now();
        }
        return null;
    }

}
