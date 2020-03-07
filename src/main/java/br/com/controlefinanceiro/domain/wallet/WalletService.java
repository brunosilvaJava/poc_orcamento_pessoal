package br.com.controlefinanceiro.domain.wallet;

import br.com.controlefinanceiro.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private WalletRepository repository;

    public WalletService(WalletRepository repository){
        this.repository = repository;
    }

    public void save(WalletVO walletVO){
        repository.save(WalletMapper.INSTANCE.VOtoEntity(walletVO));
    }

    public WalletEntity findById(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

}
