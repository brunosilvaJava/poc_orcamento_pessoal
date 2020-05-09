package br.com.controlefinanceiro.domain.creditCard.invoice;

import br.com.controlefinanceiro.domain.creditCard.CreditCardEntity;
import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INVOICE")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INVOICE")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusInvoiceType statusInvoiceType;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "DATE_PAYMENT")
    private LocalDate paymentDate;

    @Column(name = "VALUE_PAYMENT", nullable = false)
    private BigDecimal valuePayment;

    @ManyToOne
    @JoinColumn(name = "ID_PAYMENT_METHOD", nullable = false)
    private CreditCardEntity creditCardEntity;

}
