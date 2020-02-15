package br.com.controlefinanceiro.domain.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "installment_operation")
public class InstallmentOperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_installment_operation")
    private Long id;

    @Column(name = "date_hour_payment")
    private LocalDateTime dateHourPayment;

    @Column(name = "date_prevision_payment")
    private LocalDateTime datePrevisionPayment;

    @Column(name = "value_prevision")
    private BigDecimal valuePrevision;

    @Column(name = "value_payment")
    private BigDecimal valorPayment;

    @ManyToOne
    @JoinColumn(name = "id_operation")
    private OperationEntity operation;

}
