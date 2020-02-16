package br.com.controlefinanceiro.domain.converters;

import br.com.controlefinanceiro.domain.model.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.vo.PaymentMethodVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentFormMapper {

    PaymentFormMapper INSTANCE = Mappers.getMapper(PaymentFormMapper.class);

    PaymentMethodVO entityToVo(PaymentMethodEntity paymentMethodEntity);

    PaymentMethodEntity voToEntity(PaymentMethodVO paymentMethodVO);

    List<PaymentMethodVO> entitysToVos(List<PaymentMethodEntity> paymentMethodsEntity);

}
