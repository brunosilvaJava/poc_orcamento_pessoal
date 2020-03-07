package br.com.controlefinanceiro.domain.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletVO {

    private Long id;

    private String description;

    private BigDecimal overdraft;

}
