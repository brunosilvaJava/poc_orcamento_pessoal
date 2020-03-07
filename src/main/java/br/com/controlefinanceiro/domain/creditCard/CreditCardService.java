package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodService;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodType;
import br.com.controlefinanceiro.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditCardService {

    private CreditCardRepository repository;

    private PaymentMethodService paymentMethodService;

    @Autowired
    public CreditCardService(CreditCardRepository repository,PaymentMethodService paymentMethodService){
        this.repository = repository;
        this.paymentMethodService = paymentMethodService;
    }

    public void create(CreditCardVO creditCardVO) {

        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.voToEntity(creditCardVO);

        PaymentMethodEntity paymentMethodEntity = PaymentMethodEntity.builder()
                .description(creditCardVO.getDescription())
                .paymentMethodType(PaymentMethodType.CREDIT_CARD)
                .build();

        paymentMethodService.create(paymentMethodEntity);

        creditCardEntity.setPaymentMethodEntity(paymentMethodEntity);

        repository.save(creditCardEntity);

    }

    public List<CreditCardVO> findAll() {
        return CreditCardMapper.INSTANCE.entitysToVOs(repository.findAll());
    }

    public CreditCardVO findById(Long idCreditCard) {
        return CreditCardMapper.INSTANCE.entityToVO(repository.findById(idCreditCard).orElseThrow(NotFoundException::new));
    }

    public CreditCardEntity findByPaymentMethod(PaymentMethodEntity paymentMethodEntity) {
        return repository.findByPaymentMethodEntity(paymentMethodEntity);
    }

    public void update(CreditCardVO creditCardVO) {
        repository.save(CreditCardMapper.INSTANCE.voToEntity(creditCardVO));
    }

    public LocalDate getDateDue(LocalDate dataBay, CreditCardEntity creditCardEntity){

        LocalDate dateAux = LocalDate.from(dataBay);

        int dayBay = dataBay.getDayOfMonth();
        int dayClosing = creditCardEntity.getDayClosingEnvoice().intValue();

        if(dayBay >= dayClosing){
            dateAux = dateAux.plusMonths(1);
        }

        return dateAux.withDayOfMonth(creditCardEntity.getDayPay());
    }

}
