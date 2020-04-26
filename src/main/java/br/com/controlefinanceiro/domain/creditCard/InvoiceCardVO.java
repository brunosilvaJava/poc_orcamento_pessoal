package br.com.controlefinanceiro.domain.creditCard;

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

    private String creditCard;

    private LocalDate dateDue;

    private BigDecimal totalValue;

}
