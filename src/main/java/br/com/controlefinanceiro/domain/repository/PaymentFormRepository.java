package br.com.controlefinanceiro.domain.repository;

import br.com.controlefinanceiro.domain.model.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentFormRepository extends JpaRepository<PaymentMethodEntity, Long> {

}
