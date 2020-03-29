package br.com.controlefinanceiro.domain.creditCard;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-16T07:32:57-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class CreditCardMapperImpl implements CreditCardMapper {

    @Override
    public CreditCardVO entityToVO(CreditCardEntity creditCardEntity) {
        if ( creditCardEntity == null ) {
            return null;
        }

        CreditCardVO creditCardVO = new CreditCardVO();

        creditCardVO.setId( creditCardEntity.getId() );
        creditCardVO.setValueLimit( creditCardEntity.getValueLimit() );
        creditCardVO.setDayClosingEnvoice( creditCardEntity.getDayClosingEnvoice() );
        creditCardVO.setDayPay( creditCardEntity.getDayPay() );

        return creditCardVO;
    }

    @Override
    public CreditCardEntity voToEntity(CreditCardVO creditCardVO) {
        if ( creditCardVO == null ) {
            return null;
        }

        CreditCardEntity creditCardEntity = new CreditCardEntity();

        creditCardEntity.setId( creditCardVO.getId() );
        creditCardEntity.setValueLimit( creditCardVO.getValueLimit() );
        creditCardEntity.setDayClosingEnvoice( creditCardVO.getDayClosingEnvoice() );
        creditCardEntity.setDayPay( creditCardVO.getDayPay() );

        return creditCardEntity;
    }

    @Override
    public List<CreditCardVO> entitysToVOs(List<CreditCardEntity> creditCardEntityList) {
        if ( creditCardEntityList == null ) {
            return null;
        }

        List<CreditCardVO> list = new ArrayList<CreditCardVO>( creditCardEntityList.size() );
        for ( CreditCardEntity creditCardEntity : creditCardEntityList ) {
            list.add( entityToVO( creditCardEntity ) );
        }

        return list;
    }
}
