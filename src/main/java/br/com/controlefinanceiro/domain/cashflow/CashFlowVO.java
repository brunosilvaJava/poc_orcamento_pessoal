package br.com.controlefinanceiro.domain.cashflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CashFlowVO implements Comparable<CashFlowVO>{

    private LocalDate date;

    private BigDecimal openingBalance;

    private BigDecimal deposit;

    private BigDecimal outflow;

    public void sumDeposit(BigDecimal deposit){
        setDeposit(getDeposit().add(deposit));
    }

    public void sumOutflow(BigDecimal outflow){
        if(outflow.signum() < 0){
            outflow = outflow.negate();
        }
        setOutflow(getOutflow().add(outflow));
    }

    public BigDecimal getBalance(){
        return getOpeningBalance().add(getDeposit().subtract(getOutflow()));
    }

    public BigDecimal getOpeningBalance(){
        if(Objects.isNull(openingBalance)){
            openingBalance = BigDecimal.ZERO;
        }
        return openingBalance;
    }

    public BigDecimal getDeposit(){
        if(Objects.isNull(deposit)){
            deposit = BigDecimal.ZERO;
        }
        return deposit;
    }

    public BigDecimal getOutflow(){
        if(Objects.isNull(outflow)){
            outflow = BigDecimal.ZERO;
        }
        return outflow;
    }

    @Override
    public int compareTo(CashFlowVO cashFlowVO) {
        return date.compareTo(cashFlowVO.date);
    }

}
