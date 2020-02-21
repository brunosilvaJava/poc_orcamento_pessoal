package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodService;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodVO;
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
@RequestMapping(value = "/api/payment-method", produces = "application/json")
public class PaymentMethodEndpoint {

    private PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodEndpoint(PaymentMethodService paymentMethodService){
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping
    public void create(@RequestBody PaymentMethodVO paymentMethodVO){
        paymentMethodService.create(paymentMethodVO);
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(paymentMethodService.findAll());
    }

    @GetMapping("/{idPaymentMethod}")
    public ResponseEntity findById(@PathVariable(name = "idPaymentMethod") Long idPaymentMethod){
        return ResponseEntity.ok(paymentMethodService.findById(idPaymentMethod));
    }

    @PutMapping("/{idPaymentMethod}")
    public void update(@RequestBody PaymentMethodVO paymentMethodVO){
        paymentMethodService.update(paymentMethodVO);
    }

}
