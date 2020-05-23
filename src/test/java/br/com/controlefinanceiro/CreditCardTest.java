package br.com.controlefinanceiro;

import br.com.controlefinanceiro.domain.creditCard.CreditCardEntity;
import br.com.controlefinanceiro.domain.creditCard.CreditCardRepository;
import br.com.controlefinanceiro.domain.creditCard.CreditCardService;
import br.com.controlefinanceiro.domain.creditCard.invoice.InvoiceService;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.wallet.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreditCardTest {

    @InjectMocks
    private CreditCardService creditCardService;

    @Mock
    private CreditCardRepository repository;

    @Test
    public void getDateDue20DayClosing05EqualsDayBuyTest(){
        assertDateDue(20,05,05, Month.FEBRUARY);
    }

    @Test
    public void getDateDue20DayClosing05AfterDayBuyTest(){
        assertDateDue(20,05,06, Month.FEBRUARY);
    }

    @Test
    public void getDateDue20DayClosing05BeforeDayBuyTest(){
        assertDateDue(20,05,04, Month.JANUARY);
    }

    @Test
    public void getDateDue05DayClosing20EqualsDayBuyTest(){
        assertDateDue(05, 20, 20, Month.MARCH);
    }

    @Test
    public void getDateDue05DayClosing20AfterDayBuyTest(){
        assertDateDue(05, 20, 21, Month.MARCH);
    }

    @Test
    public void getDateDue05DayClosing20BeforeayBuyTest(){
        assertDateDue(05, 20, 19, Month.FEBRUARY);
    }

    @Test
    private void assertDateDue(Integer dayDue, Integer dayClosing, Integer dayBuy, Month monthDue){
        LocalDate dataBuy = LocalDate.of(2020, Month.JANUARY, dayBuy);
        LocalDate dateDue = LocalDate.of(2020, monthDue, dayDue);
        when(repository.findById(1l)).thenReturn(mockPaymentMethod(dayClosing, dayDue));
        LocalDate dateDueReturn = creditCardService.getDateDue(dataBuy, PaymentMethodEntity.builder().id(1l).build());
        Assertions.assertTrue(dateDueReturn.equals(dateDue));
    }

    private Optional<CreditCardEntity> mockPaymentMethod(Integer dayClosing, Integer dayDue) {
        CreditCardEntity creditCardEntity = new CreditCardEntity();
        creditCardEntity.setDayClosingEnvoice(dayClosing);
        creditCardEntity.setDayPay(dayDue);
        return Optional.of(creditCardEntity);
    }
}
