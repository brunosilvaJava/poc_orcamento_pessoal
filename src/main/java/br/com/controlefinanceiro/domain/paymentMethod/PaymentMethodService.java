package br.com.controlefinanceiro.domain.paymentMethod;

import br.com.controlefinanceiro.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository){
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public void create(PaymentMethodVO paymentMethodVO) {
        paymentMethodRepository.save(PaymentMethodMapper.INSTANCE.voToEntity(paymentMethodVO));
    }

    public void update(PaymentMethodVO paymentMethodVO) {
        paymentMethodRepository.save(voToEntity(paymentMethodVO));
    }

    @Cacheable("getPaymemtMethodVOAll")
    public List<PaymentMethodVO> findAll() {
        return entitysToVos(paymentMethodRepository.findAll());
    }

    @Cacheable(cacheNames = "getPaymemtMethodById", key = "#idPaymentMethod")
    public PaymentMethodEntity findById2(Long idPaymentMethod) {
        return paymentMethodRepository.findById(idPaymentMethod).orElseThrow(NotFoundException::new);
    }

    @Cacheable(cacheNames = "getPaymemtVOById", key = "#idPaymentMethod")
    public PaymentMethodVO findById(Long idPaymentMethod) {
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
