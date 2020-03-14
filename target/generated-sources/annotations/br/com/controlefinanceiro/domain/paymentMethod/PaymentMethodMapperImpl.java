package br.com.controlefinanceiro.domain.paymentMethod;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-14T09:40:39-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class PaymentMethodMapperImpl implements PaymentMethodMapper {

    @Override
    public PaymentMethodVO entityToVo(PaymentMethodEntity paymentMethodEntity) {
        if ( paymentMethodEntity == null ) {
            return null;
        }

        PaymentMethodVO paymentMethodVO = new PaymentMethodVO();

        paymentMethodVO.setId( paymentMethodEntity.getId() );
        paymentMethodVO.setDescription( paymentMethodEntity.getDescription() );
        paymentMethodVO.setPaymentMethodType( paymentMethodEntity.getPaymentMethodType() );

        return paymentMethodVO;
    }

    @Override
    public PaymentMethodEntity voToEntity(PaymentMethodVO paymentMethodVO) {
        if ( paymentMethodVO == null ) {
            return null;
        }

        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();

        paymentMethodEntity.setId( paymentMethodVO.getId() );
        paymentMethodEntity.setDescription( paymentMethodVO.getDescription() );
        paymentMethodEntity.setPaymentMethodType( paymentMethodVO.getPaymentMethodType() );

        return paymentMethodEntity;
    }

    @Override
    public List<PaymentMethodVO> entitysToVos(List<PaymentMethodEntity> paymentMethodsEntity) {
        if ( paymentMethodsEntity == null ) {
            return null;
        }

        List<PaymentMethodVO> list = new ArrayList<PaymentMethodVO>( paymentMethodsEntity.size() );
        for ( PaymentMethodEntity paymentMethodEntity : paymentMethodsEntity ) {
            list.add( entityToVo( paymentMethodEntity ) );
        }

        return list;
    }
}
