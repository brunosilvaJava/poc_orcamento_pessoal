package br.com.controlefinanceiro.rest;

import br.com.controlefinanceiro.domain.wallet.WalletService;
import br.com.controlefinanceiro.domain.wallet.WalletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/wallet", produces = "application/json")
public class WalletEndpoint {

    private WalletService walletService;

    @Autowired
    public WalletEndpoint(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping
    public void save(@RequestBody WalletVO walletVO){
        walletService.save(walletVO);
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(walletService.findAll());

    }

}
