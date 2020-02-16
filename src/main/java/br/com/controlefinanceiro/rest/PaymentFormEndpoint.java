package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.service.PaymentFormService;
import br.com.controlefinanceiro.domain.vo.PaymentMethodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/payment-form", produces = "application/json", consumes = "application/json")
public class PaymentFormEndpoint {

    private PaymentFormService paymentFormService;

    @Autowired
    public PaymentFormEndpoint(PaymentFormService paymentFormService){
        this.paymentFormService = paymentFormService;
    }

    @PostMapping
    public void create(@RequestBody PaymentMethodVO paymentMethodVO){
        paymentFormService.create(paymentMethodVO);
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(paymentFormService.findAll());
    }

}
