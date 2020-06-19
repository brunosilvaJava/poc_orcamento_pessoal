package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.operation.OperationService;
import br.com.controlefinanceiro.domain.operation.OperationVO;
import br.com.controlefinanceiro.domain.operation.PaymentType;
import br.com.controlefinanceiro.domain.recurrent.RecurrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/operation", produces = "application/json")
public class OperationEndpoint {

    private OperationService operationService;
    private RecurrentService recurrentService;

    @Autowired
    public OperationEndpoint(OperationService operationService, RecurrentService recurrentService){
        this.operationService = operationService;
        this.recurrentService = recurrentService;
    }

    @PostMapping
    public void save(@RequestBody OperationVO operationVO){
        operationService.create(operationVO);
    }

}
