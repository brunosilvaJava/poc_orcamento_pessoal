package br.com.controlefinanceiro.domain.vo;

import br.com.controlefinanceiro.domain.enums.OperationType;
import br.com.controlefinanceiro.domain.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationVO {

    private String description;

    private BigDecimal amount;

    private OperationType operationType;

    private Long idPaymentForm;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateHourBuy;

}
