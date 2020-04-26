package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.creditCard.CreditCardService;
import br.com.controlefinanceiro.domain.creditCard.CreditCardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "/api/credit-card")
@RestController
@RequestMapping(value = "/api/credit-card", produces = "application/json")
public class CreditCardEndpoint {

    private CreditCardService creditCardService;

    @Autowired
    public CreditCardEndpoint(CreditCardService creditCardService){
        this.creditCardService = creditCardService;
    }

    @ApiOperation(value = "Insere um novo cartão de crédito.")
    @PostMapping
    public void create(@RequestBody CreditCardVO creditCardVO){
        creditCardService.create(creditCardVO);
    }

    @ApiOperation(value = "Consulta todos os cartões de crédito.",
            response = CreditCardVO.class,
            responseContainer = "List")
    @GetMapping
    public ResponseEntity<List<CreditCardVO>> findAll(){
        return ResponseEntity.ok(creditCardService.findAll());
    }

    @ApiOperation(value = "Altera um cartão de crédito pelo id.")
    @PutMapping("/{idCreditCard}")
    public void update(@RequestBody CreditCardVO creditCardVO){
        creditCardService.update(creditCardVO);
    }

    @ApiOperation(value = "Consulta um cartões de crédito pelo id.")
    @GetMapping("/{idCreditCard}")
    public ResponseEntity<CreditCardVO> findById(@PathVariable(name = "idCreditCard") Long idCreditCard){
        return ResponseEntity.ok(creditCardService.findById(idCreditCard));
    }

    /*@ApiOperation(value = "Retorna a fatura de um cartão de crédito.")
    @GetMapping("/{idCreditCard}/invoice")
    public ResponseEntity findInvoiceCardById(@PathVariable(name = "idCreditCard") Long idCreditCard){
        return ResponseEntity.ok(creditCardService.findInvoiceCardById(idCreditCard));
    }*/

}
