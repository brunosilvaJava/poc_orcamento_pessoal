package br.com.controlefinanceiro;

import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodMapper;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodType;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodVO;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

public class MapperTest {

    @Test
    public void paymentMethodVoToEntity(){

        PaymentMethodVO paymentMethodVO = PaymentMethodVO.builder()
                .description("Cartão NuBank")
                .paymentMethodType(PaymentMethodType.CREDIT_CARD)
                .build();

        PaymentMethodEntity paymentMethodEntity = PaymentMethodMapper.INSTANCE.voToEntity(paymentMethodVO);

        Assert.notNull(paymentMethodEntity, "paymentMethodEntity null");
        Assert.isTrue(paymentMethodEntity.getDescription().equals(paymentMethodVO.getDescription()), "test paymentMethodVoToEntity - Descriptions for payment methods are not the same");
        Assert.isTrue(paymentMethodEntity.getPaymentMethodType() == paymentMethodVO.getPaymentMethodType(), "test paymentMethodVoToEntity - Payment types for payment methods are not the same");

    }

    @Test
    public void paymentMethodEntityToVo(){

        PaymentMethodEntity paymentMethodEntity = PaymentMethodEntity.builder()
                .description("Cartão NuBank")
                .paymentMethodType(PaymentMethodType.CREDIT_CARD)
                .build();

        PaymentMethodVO paymentMethodVO = PaymentMethodMapper.INSTANCE.entityToVo(paymentMethodEntity);

        Assert.notNull(paymentMethodVO, "test paymentMethodEntityToVo - paymentMethodVO null");
        Assert.isTrue(paymentMethodVO.getDescription().equals(paymentMethodEntity.getDescription()), "test paymentMethodEntityToVo - Descriptions for payment methods are not the same");
        Assert.isTrue(paymentMethodVO.getPaymentMethodType() == paymentMethodEntity.getPaymentMethodType(), "test paymentMethodEntityToVo - Payment types for payment methods are not the same");

    }

    @Test
    public void listPaymentMethodEntityToVo(){

        PaymentMethodEntity paymentMethodEntity1 = PaymentMethodEntity.builder()
                .description("Cartão NuBank")
                .paymentMethodType(PaymentMethodType.CREDIT_CARD)
                .build();

        PaymentMethodEntity paymentMethodEntity2 = PaymentMethodEntity.builder()
                .description("Cartão Digio")
                .paymentMethodType(PaymentMethodType.CREDIT_CARD)
                .build();

        List<PaymentMethodVO> paymentMethodsVO = PaymentMethodMapper.INSTANCE.entitysToVos(Arrays.asList(paymentMethodEntity1, paymentMethodEntity2));

        Assert.notNull(paymentMethodsVO, "test listPaymentMethodEntityToVo - list paymentMethodVO null");
        Assert.notEmpty(paymentMethodsVO, "test listPaymentMethodEntityToVo - list paymentMethodVO empty");
        Assert.isTrue(paymentMethodsVO.stream().filter(paymentMethodVO ->
                paymentMethodVO.getDescription().equals("Cartão NuBank") &&
                        paymentMethodVO.getPaymentMethodType() == PaymentMethodType.CREDIT_CARD
        ).findAny().isPresent(), "test listPaymentMethodEntityToVo - paymentMethodVO is not present");
        Assert.isTrue(paymentMethodsVO.stream().filter(paymentMethodVO ->
                paymentMethodVO.getDescription().equals("Cartão Digio") &&
                        paymentMethodVO.getPaymentMethodType() == PaymentMethodType.CREDIT_CARD
        ).findAny().isPresent(), "test listPaymentMethodEntityToVo - paymentMethodVO is not present");

    }

}
