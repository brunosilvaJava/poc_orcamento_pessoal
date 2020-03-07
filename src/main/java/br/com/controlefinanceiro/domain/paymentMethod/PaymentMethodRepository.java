package br.com.controlefinanceiro.domain.paymentMethod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Long> {

}
