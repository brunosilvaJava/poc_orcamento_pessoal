package br.com.controlefinanceiro.domain.repository;

import br.com.controlefinanceiro.domain.model.InstallmentOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentOperationRepository extends JpaRepository<InstallmentOperationEntity, Long> {
}
