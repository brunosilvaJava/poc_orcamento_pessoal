package br.com.controlefinanceiro.domain.operation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OperationRepository extends JpaRepository<OperationEntity, Long> {

}
