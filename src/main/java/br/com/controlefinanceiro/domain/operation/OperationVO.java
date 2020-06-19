package br.com.controlefinanceiro.domain.operation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationVO {

    private Long id;

    private String description;

    private BigDecimal value;

    private Integer numberInstallments;

    private OperationType operationType;

    private PaymentType paymentType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate dateBuy;

    private Long idPaymentMethod;

    private Long idWallet;

}
