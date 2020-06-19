package br.com.controlefinanceiro.domain.operation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRecurrentVO {

    private Long id;

    private String description;

    private BigDecimal value;

    private List<Integer> days;

    private PaymentRecurrentType type;

    private OperationType operationType;

    private Long idPaymentMethod;

    private Long idWallet;

}
