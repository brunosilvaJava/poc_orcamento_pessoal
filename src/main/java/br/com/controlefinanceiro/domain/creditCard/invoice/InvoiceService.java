package br.com.controlefinanceiro.domain.creditCard.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    public void create(InvoiceEntity invoiceEntity){
        this.invoiceRepository.save(invoiceEntity);
    }

    public Optional<InvoiceEntity> findByIdAndCreditCardEntityId(Long idInvoice, Long idCreditCard){
        return Optional.of(invoiceRepository.findByIdAndCreditCardEntityId(idInvoice, idCreditCard));
    }

    public Optional<InvoiceEntity> findByDueDateAndCreditCardEntityId(LocalDate dueDate, Long idCreditCard){
        return Optional.of(invoiceRepository.findByDueDateAndCreditCardEntityId(dueDate, idCreditCard));
    }

}
