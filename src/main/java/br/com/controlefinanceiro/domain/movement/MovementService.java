package br.com.controlefinanceiro.domain.movement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovementService {

    private MovementRepository repository;

    @Autowired
    public MovementService(MovementRepository repository){
        this.repository = repository;
    }

    public void create(MovementEntity installmentOperationEntity) {
        repository.save(installmentOperationEntity);
    }

    public List<MovementEntity> findAll(){
        return repository.findAll();
    }

    public BigDecimal sumValuePaymentByDatePaymentLessThanEqual(LocalDate date){
        return repository.sumValuePaymentByDatePaymentLessThanEqual(date);
    }

}
