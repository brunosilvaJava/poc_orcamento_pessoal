package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.creditCard.invoice.InvoiceCardDetailVO;
import br.com.controlefinanceiro.domain.creditCard.invoice.InvoiceEntity;
import br.com.controlefinanceiro.domain.creditCard.invoice.InvoiceService;
import br.com.controlefinanceiro.domain.operation.StatusPaymentType;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodType;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import br.com.controlefinanceiro.domain.wallet.WalletService;
import br.com.controlefinanceiro.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Objects;

@Service
public class CreditCardService {

    private CreditCardRepository repository;
    private InvoiceService invoiceService;
    private WalletService walletService;

    @Autowired
    public CreditCardService(CreditCardRepository repository,
                             InvoiceService invoiceService,
                             WalletService walletService){
        this.repository = repository;
        this.invoiceService = invoiceService;
        this.walletService = walletService;
    }

    public void create(CreditCardVO creditCardVO) {

        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.voToEntity(creditCardVO);
        creditCardEntity.setDescription(creditCardVO.getDescription());
        creditCardEntity.setPaymentMethodType(PaymentMethodType.CREDIT_CARD);

        if(creditCardVO.getIdWallet() != null){
            creditCardEntity.setWalletEntity(walletService.findById(creditCardVO.getIdWallet()));
        }

        repository.save(creditCardEntity);

    }

    public List<CreditCardVO> findAll() {
        return CreditCardMapper.INSTANCE.entitysToVOs(repository.findAll());
    }

    public CreditCardVO findById(Long idCreditCard) {
        return CreditCardMapper.INSTANCE.entityToVO(repository.findById(idCreditCard).orElseThrow(NotFoundException::new));
    }

    public void update(CreditCardVO creditCardVO) {

        WalletEntity walletEntity = null;

        if (creditCardVO.getIdWallet() != null){
            walletEntity = walletService.findById(creditCardVO.getIdWallet());
        }

        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.voToEntity(creditCardVO);

        creditCardEntity.setWalletEntity(walletEntity);

        repository.save(creditCardEntity);
    }

    public LocalDate getDateDue(LocalDate dataBay, PaymentMethodEntity paymentMethodEntity){

        CreditCardEntity creditCardEntity = repository.findById(paymentMethodEntity.getId()).orElseThrow(NotFoundException::new);

        LocalDate dateAux = LocalDate.from(dataBay);

        int dayBay = dataBay.getDayOfMonth();
        int dayClosing = creditCardEntity.getDayClosingEnvoice().intValue();

        if(dayBay >= dayClosing){
            dateAux = dateAux.plusMonths(1);
        }

        return dateAux.withDayOfMonth(creditCardEntity.getDayPay());
    }

    public List<InvoiceCardVO> findInvoicesCardById(Long idPaymentMethod, StatusPaymentType statusPaymentType, Month month, Year year) {

        Integer monthAux = Objects.nonNull(month) ? month.getValue() : null;
        Integer yearAux = Objects.nonNull(year) ? year.getValue() : null;

        return repository.findInvoices(idPaymentMethod, statusPaymentType, monthAux, yearAux);
    }

    public List<InvoiceCardDetailVO> findInvoiceCardDetailById(Long idCreditCard, Long idInvoice) {
        return repository.findInvoiceCardDetailById(idCreditCard, idInvoice);
    }

    @Transactional
    public void paymentInvoice(Long idCreditCard, Long idInvoice, BigDecimal value) {

        InvoiceEntity invoiceEntity = invoiceService.findByIdAndCreditCardEntityId(idInvoice, idCreditCard).orElseThrow(NotFoundException::new);
        invoiceEntity.setValuePayment(value);
        invoiceEntity.setPaymentDate(LocalDate.now());

        repository.paymentInvoice(idInvoice);
    }
}
