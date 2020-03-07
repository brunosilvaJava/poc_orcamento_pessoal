package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.cashflow.CashFlowService;
import br.com.controlefinanceiro.domain.cashflow.CashFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api/cashflow", produces = "application/json")
public class CashFlowEndpoint {

    private CashFlowService cashFlowService;

    @Autowired
    public CashFlowEndpoint(CashFlowService cashFlowService){
        this.cashFlowService = cashFlowService;
    }

    @GetMapping
    public Collection<CashFlowVO> cashFlow(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
        return cashFlowService.cashFlow(start, end);
    }

}
