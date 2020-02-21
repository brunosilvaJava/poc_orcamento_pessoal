package br.com.controlefinanceiro.domain.paymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodVO implements Serializable {

    private Long id;

    private String description;

    private PaymentMethodType paymentMethodType;

}
