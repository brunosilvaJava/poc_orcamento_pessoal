package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.creditCard.CreditCardService;
import br.com.controlefinanceiro.domain.creditCard.CreditCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/credit-card", produces = "application/json")
public class CreditCardEndpoint {

    private CreditCardService creditCardService;

    @Autowired
    public CreditCardEndpoint(CreditCardService creditCardService){
        this.creditCardService = creditCardService;
    }

    @PostMapping
    public void create(@RequestBody CreditCardVO creditCardVO){
        creditCardService.create(creditCardVO);
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(creditCardService.findAll());
    }

    @PutMapping("/{idCreditCard}")
    public void update(@RequestBody CreditCardVO creditCardVO){
        creditCardService.update(creditCardVO);
    }

    @GetMapping("/{idCreditCard}")
    public ResponseEntity findById(@PathVariable(name = "idCreditCard") Long idCreditCard){
        return ResponseEntity.ok(creditCardService.findById(idCreditCard));
    }

    @GetMapping("/{idCreditCard}/invoice")
    public ResponseEntity findInvoiceCardById(@PathVariable(name = "idCreditCard") Long idCreditCard){
        return ResponseEntity.ok(creditCardService.findInvoiceCardById(idCreditCard));
    }

}
