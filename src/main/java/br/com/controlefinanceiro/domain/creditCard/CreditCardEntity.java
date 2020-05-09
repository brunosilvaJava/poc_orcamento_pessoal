package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_card")
@PrimaryKeyJoinColumn(name="id_payment_method")
public class CreditCardEntity extends PaymentMethodEntity {

    @Column(name = "value_limit", nullable = false)
    private BigDecimal valueLimit;

    @Column(name = "day_closyng_envoice", nullable = false)
    private Integer dayClosingEnvoice;

    @Column(name = "day_pay", nullable = false)
    private Integer dayPay;

}
