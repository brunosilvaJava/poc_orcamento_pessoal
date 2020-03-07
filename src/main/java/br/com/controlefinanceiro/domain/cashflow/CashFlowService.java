package br.com.controlefinanceiro.domain.cashflow;

import br.com.controlefinanceiro.domain.movement.MovementEntity;
import br.com.controlefinanceiro.domain.movement.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

@Service
public class CashFlowService {

    private MovementService movementService;

    @Autowired
    public CashFlowService(MovementService movementService){
        this.movementService = movementService;
    }

    public Collection<CashFlowVO> cashFlow(LocalDate start, LocalDate end){

        TreeMap<LocalDate, CashFlowVO> cashFlow = new TreeMap<>();

        List<MovementEntity> movements = movementService.findAll();

        CashFlowVO cashFlowVO = null;
        LocalDate date = null;

        for (MovementEntity movement : movements) {

            date = movement.getDatePrevisionPaymentByStatus();

            if(cashFlow.containsKey(date)){
                if(movement.isDeposit()){
                    cashFlow.get(date).sumDeposit(movement.getValuePayment());
                } else {
                    cashFlow.get(date).sumOutflow(movement.getValuePayment());
                }
            } else {
                cashFlowVO = CashFlowVO.builder()
                        .date(date)
                        .build();
                if(movement.isDeposit()){
                    cashFlowVO.sumDeposit(movement.getValuePayment());
                } else {
                    cashFlowVO.sumOutflow(movement.getValuePayment());
                }
                cashFlow.put(date, cashFlowVO);
            }
        }

        Collection<CashFlowVO> cashFlowVOs = cashFlow.values();

        BigDecimal openingBalance = BigDecimal.ZERO;

        for(CashFlowVO cf : cashFlowVOs) {
            cf.setOpeningBalance(openingBalance);
            openingBalance = cf.getBalance();
        }

        return cashFlowVOs;
    }

}
