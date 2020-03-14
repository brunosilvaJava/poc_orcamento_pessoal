package br.com.controlefinanceiro.domain.paymentMethod;

import br.com.controlefinanceiro.domain.wallet.WalletService;
import br.com.controlefinanceiro.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;

    private WalletService walletService;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository){
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void create(PaymentMethodVO paymentMethodVO) {

        PaymentMethodEntity entity = PaymentMethodMapper.INSTANCE.voToEntity(paymentMethodVO);

        if(paymentMethodVO.getIdWallet() != null){
            entity.setWallet(walletService.findById(paymentMethodVO.getIdWallet()));
        }

        create(entity);
    }

    public void create(PaymentMethodEntity paymentMethodEntity) {
        paymentMethodRepository.save(paymentMethodEntity);
    }

    public void update(PaymentMethodVO paymentMethodVO) {
        paymentMethodRepository.save(voToEntity(paymentMethodVO));
    }

    //@Cacheable("getPaymemtMethodVOAll")
    public List<PaymentMethodVO> findAll() {
        return entitysToVos(paymentMethodRepository.findAll());
    }

    //@Cacheable(cacheNames = "getPaymemtMethodById", key = "#idPaymentMethod")
    public PaymentMethodEntity findEntityById(Long idPaymentMethod) {
        return paymentMethodRepository.findById(idPaymentMethod).orElseThrow(NotFoundException::new);
    }

    //@Cacheable(cacheNames = "getPaymemtVOById", key = "#idPaymentMethod")
    public PaymentMethodVO findVoById(Long idPaymentMethod) {
        return entityToVo(paymentMethodRepository.findById(idPaymentMethod).orElseThrow(NotFoundException::new));
    }

    private PaymentMethodEntity voToEntity(PaymentMethodVO paymentMethodVO){
        return PaymentMethodMapper.INSTANCE.voToEntity(paymentMethodVO);
    }

    private PaymentMethodVO entityToVo(PaymentMethodEntity paymentMethodEntity){
        return PaymentMethodMapper.INSTANCE.entityToVo(paymentMethodEntity);
    }

    private List<PaymentMethodVO> entitysToVos(List<PaymentMethodEntity> paymentMethodEntityList){
        return PaymentMethodMapper.INSTANCE.entitysToVos(paymentMethodEntityList);
    }

}
