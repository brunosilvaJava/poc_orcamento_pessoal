package br.com.controlefinanceiro.domain.creditCard;

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

}
