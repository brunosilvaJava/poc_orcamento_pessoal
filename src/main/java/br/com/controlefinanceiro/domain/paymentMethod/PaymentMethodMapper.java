package br.com.controlefinanceiro.domain.paymentMethod;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMethodMapper {

    PaymentMethodMapper INSTANCE = Mappers.getMapper(PaymentMethodMapper.class);

    PaymentMethodVO entityToVo(PaymentMethodEntity paymentMethodEntity);

    PaymentMethodEntity voToEntity(PaymentMethodVO paymentMethodVO);

    List<PaymentMethodVO> entitysToVos(List<PaymentMethodEntity> paymentMethodsEntity);

}
