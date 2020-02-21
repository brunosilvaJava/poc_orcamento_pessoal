package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.operation.OperationService;
import br.com.controlefinanceiro.domain.operation.OperationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/operation", produces = "application/json", consumes = "application/json")
public class OperationEndpoint {

    private OperationService operationService;

    @Autowired
    public OperationEndpoint(OperationService operationService){
        this.operationService = operationService;
    }

    @PostMapping
    public void save(@RequestBody OperationVO operationVO){
        operationService.save(operationVO);
    }

}
