package br.com.controlefinanceiro.recurrent;

import br.com.controlefinanceiro.domain.operation.OperationType;
import br.com.controlefinanceiro.domain.operation.PaymentRecurrentType;
import br.com.controlefinanceiro.domain.operation.PaymentRecurrentVO;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodService;
import br.com.controlefinanceiro.domain.recurrent.RecurrentEntity;
import br.com.controlefinanceiro.domain.recurrent.RecurrentMapper;
import br.com.controlefinanceiro.domain.recurrent.RecurrentRepository;
import br.com.controlefinanceiro.domain.recurrent.RecurrentService;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import br.com.controlefinanceiro.domain.wallet.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class RecurrentTest {

    @InjectMocks
    private RecurrentService recurrentService;

    @Mock
    private RecurrentRepository recurrentRepository;

    @Mock
    private WalletService walletService;

    @Mock
    private PaymentMethodService paymentMethodService;

    @Test
    public void createMovementRecurrentTest(){

        Mockito.when(walletService.findById(1L)).thenReturn(WalletEntity.builder().build());
        Mockito.when(paymentMethodService.findEntityById(1L)).thenReturn(PaymentMethodEntity.builder().build());

        recurrentService.create(PaymentRecurrentVO.builder()
                .description("Test Recurrent")
                .value(BigDecimal.valueOf(1000))
                .days(Arrays.asList(1,2,3))
                .operationType(OperationType.OUTFLOW)
                .type(PaymentRecurrentType.WEEKLY)
                .idWallet(1L)
                .idPaymentMethod(1L)
                .build());

        ArgumentCaptor<RecurrentEntity> argument = ArgumentCaptor.forClass(RecurrentEntity.class);
        Mockito.verify(recurrentRepository).save(argument.capture());
        RecurrentEntity recurrentEntity = argument.getValue();

        Assertions.assertEquals("Test Recurrent", recurrentEntity.getDescription());
        Assertions.assertEquals(0, BigDecimal.valueOf(1000).compareTo(recurrentEntity.getValue()));
        Assertions.assertEquals(OperationType.OUTFLOW, recurrentEntity.getOperationType());
        Assertions.assertEquals(PaymentRecurrentType.WEEKLY, recurrentEntity.getType());
        Assertions.assertNotNull(recurrentEntity.getWallet());
        Assertions.assertNotNull(recurrentEntity.getPaymentMethod());
        Assertions.assertNotNull(recurrentEntity.getDays());
        Assertions.assertFalse(recurrentEntity.getDays().isEmpty());
        Assertions.assertEquals(1, recurrentEntity.getDays().get(0));
        Assertions.assertEquals(2, recurrentEntity.getDays().get(1));
        Assertions.assertEquals(3, recurrentEntity.getDays().get(2));
    }

    @Test
    public void recurrentMapperTest(){

        RecurrentEntity recurrentEntity = RecurrentMapper.INSTANCE.voToEntity(PaymentRecurrentVO.builder()
                .id(1L)
                .description("Test Recurrent")
                .value(BigDecimal.valueOf(1000))
                .operationType(OperationType.DEPOSIT)
                .days(Arrays.asList(1,2,3))
                .type(PaymentRecurrentType.MONTHLY)
                .build());

        Assertions.assertEquals(1l, recurrentEntity.getId());
        Assertions.assertEquals("Test Recurrent", recurrentEntity.getDescription());
        Assertions.assertEquals(0, BigDecimal.valueOf(1000).compareTo(recurrentEntity.getValue()));
        Assertions.assertEquals(OperationType.DEPOSIT, recurrentEntity.getOperationType());
        Assertions.assertEquals(PaymentRecurrentType.MONTHLY, recurrentEntity.getType());
        Assertions.assertNotNull(recurrentEntity.getDays());
        Assertions.assertFalse(recurrentEntity.getDays().isEmpty());
        Assertions.assertEquals(1, recurrentEntity.getDays().get(0));
        Assertions.assertEquals(2, recurrentEntity.getDays().get(1));
        Assertions.assertEquals(3, recurrentEntity.getDays().get(2));

    }

}
