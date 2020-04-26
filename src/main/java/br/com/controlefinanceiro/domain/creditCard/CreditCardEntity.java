package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit_card")
    private Long id;

    @Column(name = "value_limit", nullable = false)
    private BigDecimal valueLimit;

    @Column(name = "day_closyng_envoice", nullable = false)
    private Integer dayClosingEnvoice;

    @Column(name = "day_pay", nullable = false)
    private Integer dayPay;

    @ManyToOne
    @JoinColumn(name = "id_payment_method")
    private PaymentMethodEntity paymentMethodEntity;

}
