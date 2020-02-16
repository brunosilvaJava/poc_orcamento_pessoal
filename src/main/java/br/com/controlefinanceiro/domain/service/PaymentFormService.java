package br.com.controlefinanceiro.domain.service;

import br.com.controlefinanceiro.domain.converters.PaymentFormMapper;
import br.com.controlefinanceiro.domain.repository.PaymentFormRepository;
import br.com.controlefinanceiro.domain.vo.PaymentMethodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentFormService {

    private PaymentFormRepository paymentFormRepository;

    @Autowired
    public PaymentFormService(PaymentFormRepository paymentFormRepository){
        this.paymentFormRepository = paymentFormRepository;
    }

    public void create(PaymentMethodVO paymentMethodVO) {
        paymentFormRepository.save(PaymentFormMapper.INSTANCE.voToEntity(paymentMethodVO));
    }

    public List<PaymentMethodVO> findAll() {
        return PaymentFormMapper.INSTANCE.entitysToVos(paymentFormRepository.findAll());
    }

}
