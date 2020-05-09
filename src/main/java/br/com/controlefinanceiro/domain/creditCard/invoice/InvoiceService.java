package br.com.controlefinanceiro.domain.creditCard.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    public Optional<InvoiceEntity> findByIdAndCreditCardEntityId(Long idInvoice, Long idCreditCard){
        return Optional.of(invoiceRepository.findByIdAndCreditCardEntityId(idInvoice, idCreditCard));
    }

}
