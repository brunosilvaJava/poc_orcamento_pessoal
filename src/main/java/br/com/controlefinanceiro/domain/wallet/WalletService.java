package br.com.controlefinanceiro.domain.wallet;

import br.com.controlefinanceiro.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private WalletRepository repository;

    @Autowired
    public WalletService(WalletRepository repository){
        this.repository = repository;
    }

    public void save(WalletVO walletVO){
        repository.save(WalletMapper.INSTANCE.VOtoEntity(walletVO));
    }

    public WalletEntity findById(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<WalletVO> findAll(){
        return WalletMapper.INSTANCE.entitiesToVOs(repository.findAll());
    }

}
