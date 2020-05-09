package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardVO {

    private Long id;

    private String description;

    private BigDecimal valueLimit;

    private Integer dayClosingEnvoice;

    private Integer dayPay;

    private Long idWallet;

    @JsonIgnore
    private PaymentMethodType paymentMethodType = PaymentMethodType.CREDIT_CARD;

}
