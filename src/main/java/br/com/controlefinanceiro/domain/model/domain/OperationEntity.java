package br.com.controlefinanceiro.domain.model.domain;

import br.com.controlefinanceiro.domain.model.enums.OperationType;
import br.com.controlefinanceiro.domain.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "operation")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operation")
    private Long id;

    @Column(name = "description")
    private String description;

    @Enumerated
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @Enumerated
    @Column(name = "operation_type")
    private OperationType operationType;

}
