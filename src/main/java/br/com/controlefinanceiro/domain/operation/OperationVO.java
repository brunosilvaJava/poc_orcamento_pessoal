package br.com.controlefinanceiro.domain.operation;

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

    private Long id;

    private String description;

    private BigDecimal totalValue;

    private Integer numberInstallments;

    private OperationType operationType;

    private Long idPaymentMethod;

    private PaymentType paymentType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateHourBuy;

}
