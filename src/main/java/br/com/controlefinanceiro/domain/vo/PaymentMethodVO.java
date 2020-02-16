package br.com.controlefinanceiro.domain.vo;

import br.com.controlefinanceiro.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodVO {

    private Long id;

    private String description;

    private PaymentType paymentType;

}
