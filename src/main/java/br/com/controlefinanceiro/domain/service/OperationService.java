package br.com.controlefinanceiro.domain.service;

import br.com.controlefinanceiro.domain.repository.OperationRepository;
import br.com.controlefinanceiro.domain.vo.OperationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    private OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    public void save(OperationVO operationVO){

    }

}