package br.com.controlefinanceiro.domain.recurrent;

import br.com.controlefinanceiro.domain.operation.PaymentRecurrentVO;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodService;
import br.com.controlefinanceiro.domain.wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecurrentService {

    private RecurrentRepository recurrentRepository;
    private WalletService walletService;
    private PaymentMethodService paymentMethodService;

    @Autowired
    public RecurrentService(RecurrentRepository recurrentRepository, WalletService walletService, PaymentMethodService paymentMethodService){
        this.walletService = walletService;
        this.paymentMethodService = paymentMethodService;
        this.recurrentRepository = recurrentRepository;
    }

    @Transactional
    public void create(PaymentRecurrentVO paymentRecurrentVO){

        RecurrentEntity recurrentEntity = RecurrentMapper.INSTANCE.voToEntity(paymentRecurrentVO);

        recurrentEntity.setWallet(walletService.findById(paymentRecurrentVO.getIdWallet()));
        recurrentEntity.setPaymentMethod(paymentMethodService.findEntityById(paymentRecurrentVO.getIdPaymentMethod()));

        recurrentRepository.save(recurrentEntity);
    }

    @Transactional
    public void update(PaymentRecurrentVO paymentRecurrentVO){
        recurrentRepository.save(RecurrentMapper.INSTANCE.voToEntity(paymentRecurrentVO));
    }

    public List<PaymentRecurrentVO> getAll(){
        return RecurrentMapper.INSTANCE.entitiesToVos(recurrentRepository.findAll());
    }

}
