package br.com.controlefinanceiro;

import br.com.controlefinanceiro.domain.converters.PaymentFormMapper;
import br.com.controlefinanceiro.domain.enums.PaymentType;
import br.com.controlefinanceiro.domain.model.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.vo.PaymentMethodVO;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

public class MapperTest {

    @Test
    public void paymentMethodVoToEntity(){

        PaymentMethodVO paymentMethodVO = PaymentMethodVO.builder()
                .description("Cartão NuBank")
                .paymentType(PaymentType.CREDIT_CARD)
                .build();

        PaymentMethodEntity paymentMethodEntity = PaymentFormMapper.INSTANCE.voToEntity(paymentMethodVO);

        Assert.notNull(paymentMethodEntity, "paymentMethodEntity null");
        Assert.isTrue(paymentMethodEntity.getDescription().equals(paymentMethodVO.getDescription()), "test paymentMethodVoToEntity - Descriptions for payment methods are not the same");
        Assert.isTrue(paymentMethodEntity.getPaymentType() == paymentMethodVO.getPaymentType(), "test paymentMethodVoToEntity - Payment types for payment methods are not the same");

    }

    @Test
    public void paymentMethodEntityToVo(){

        PaymentMethodEntity paymentMethodEntity = PaymentMethodEntity.builder()
                .description("Cartão NuBank")
                .paymentType(PaymentType.CREDIT_CARD)
                .build();

        PaymentMethodVO paymentMethodVO = PaymentFormMapper.INSTANCE.entityToVo(paymentMethodEntity);

        Assert.notNull(paymentMethodVO, "test paymentMethodEntityToVo - paymentMethodVO null");
        Assert.isTrue(paymentMethodVO.getDescription().equals(paymentMethodEntity.getDescription()), "test paymentMethodEntityToVo - Descriptions for payment methods are not the same");
        Assert.isTrue(paymentMethodVO.getPaymentType() == paymentMethodEntity.getPaymentType(), "test paymentMethodEntityToVo - Payment types for payment methods are not the same");

    }

    @Test
    public void listPaymentMethodEntityToVo(){

        PaymentMethodEntity paymentMethodEntity1 = PaymentMethodEntity.builder()
                .description("Cartão NuBank")
                .paymentType(PaymentType.CREDIT_CARD)
                .build();

        PaymentMethodEntity paymentMethodEntity2 = PaymentMethodEntity.builder()
                .description("Cartão Digio")
                .paymentType(PaymentType.CREDIT_CARD)
                .build();

        List<PaymentMethodVO> paymentMethodsVO = PaymentFormMapper.INSTANCE.entitysToVos(Arrays.asList(paymentMethodEntity1, paymentMethodEntity2));

        Assert.notNull(paymentMethodsVO, "test listPaymentMethodEntityToVo - list paymentMethodVO null");
        Assert.notEmpty(paymentMethodsVO, "test listPaymentMethodEntityToVo - list paymentMethodVO empty");
        Assert.isTrue(paymentMethodsVO.stream().filter(paymentMethodVO ->
                paymentMethodVO.getDescription().equals("Cartão NuBank") && paymentMethodVO.getPaymentType() == PaymentType.CREDIT_CARD
        ).findAny().isPresent(), "test listPaymentMethodEntityToVo - paymentMethodVO is not present");
        Assert.isTrue(paymentMethodsVO.stream().filter(paymentMethodVO ->
                paymentMethodVO.getDescription().equals("Cartão Digio") && paymentMethodVO.getPaymentType() == PaymentType.CREDIT_CARD
        ).findAny().isPresent(), "test listPaymentMethodEntityToVo - paymentMethodVO is not present");

    }

}
