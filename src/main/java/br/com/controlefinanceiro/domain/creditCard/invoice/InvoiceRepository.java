package br.com.controlefinanceiro.domain.creditCard.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    InvoiceEntity findByIdAndCreditCardEntityId(Long id, Long idCreditCard);

    InvoiceEntity findByDueDateAndCreditCardEntityId(LocalDate dueDate, Long idCreditCard);
}
