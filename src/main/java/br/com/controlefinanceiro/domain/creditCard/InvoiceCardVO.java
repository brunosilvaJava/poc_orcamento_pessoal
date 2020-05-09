package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCardVO {

    private Long id;

    private String creditCard;

    private LocalDate dateDue;

    private BigDecimal totalValue;

    private StatusPaymentType statusPaymentType;

}
