package br.com.controlefinanceiro.domain.repository;

import br.com.controlefinanceiro.domain.model.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

}
