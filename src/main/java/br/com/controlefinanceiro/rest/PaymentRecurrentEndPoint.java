package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.operation.PaymentRecurrentVO;
import br.com.controlefinanceiro.domain.recurrent.RecurrentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/payment-recurrent", produces = "application/json")
public class PaymentRecurrentEndPoint {

    private RecurrentService recurrentService;

    public PaymentRecurrentEndPoint(RecurrentService recurrentService){
        this.recurrentService = recurrentService;
    }

    @GetMapping
    public List<PaymentRecurrentVO> getAll(){
        return recurrentService.getAll();
    }

    @PostMapping
    public void save(@RequestBody PaymentRecurrentVO paymentRecurrentVO){
        recurrentService.create(paymentRecurrentVO);
    }

    @PutMapping
    public void update(@RequestBody PaymentRecurrentVO paymentRecurrentVO){
        recurrentService.create(paymentRecurrentVO);
    }

}
