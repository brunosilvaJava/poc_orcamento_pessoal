package br.com.controlefinanceiro.domain.creditCard.invoice;

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
public class InvoiceCardDetailVO {

    private LocalDate dateBuy;
    private String description;
    private BigDecimal valuePayment;

    public BigDecimal getValuePayment(){
        return valuePayment.negate();
    }

}
