package br.com.controlefinanceiro.domain.creditCard.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    InvoiceEntity findByIdAndCreditCardEntityId(Long id, Long idCreditCard);

}
