package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodService;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodType;
import br.com.controlefinanceiro.domain.wallet.WalletService;
import br.com.controlefinanceiro.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditCardService {

    private CreditCardRepository repository;

    private PaymentMethodService paymentMethodService;

    private WalletService walletService;

    @Autowired
    public CreditCardService(CreditCardRepository repository,
                             PaymentMethodService paymentMethodService,
                             WalletService walletService){
        this.repository = repository;
        this.paymentMethodService = paymentMethodService;
        this.walletService = walletService;
    }

    public void create(CreditCardVO creditCardVO) {

        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.voToEntity(creditCardVO);

        PaymentMethodEntity paymentMethodEntity = PaymentMethodEntity.builder()
                .description(creditCardVO.getDescription())
                .paymentMethodType(PaymentMethodType.CREDIT_CARD)
                .build();

        if(creditCardVO.getIdWallet() != null){
            paymentMethodEntity.setWalletEntity(walletService.findById(creditCardVO.getIdWallet()));
        }

        paymentMethodService.create(paymentMethodEntity);

        creditCardEntity.setPaymentMethodEntity(paymentMethodEntity);

        repository.save(creditCardEntity);

    }

    public List<CreditCardVO> findAll() {
        return CreditCardMapper.INSTANCE.entitysToVOs(repository.find());
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

    public InvoiceCardVO findInvoiceCardById(Long idCreditCard) {

        return null;
    }
}
