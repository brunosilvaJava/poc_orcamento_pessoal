package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-03T13:47:56-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class CreditCardMapperImpl implements CreditCardMapper {

    @Override
    public CreditCardVO entityToVO(CreditCardEntity creditCardEntity) {
        if ( creditCardEntity == null ) {
            return null;
        }

        CreditCardVO creditCardVO = new CreditCardVO();

        creditCardVO.setDescription( creditCardEntity.getDescription() );
        creditCardVO.setIdWallet( creditCardEntityWalletEntityId( creditCardEntity ) );
        creditCardVO.setId( creditCardEntity.getId() );
        creditCardVO.setValueLimit( creditCardEntity.getValueLimit() );
        creditCardVO.setDayClosingEnvoice( creditCardEntity.getDayClosingEnvoice() );
        creditCardVO.setDayPay( creditCardEntity.getDayPay() );
        creditCardVO.setPaymentMethodType( creditCardEntity.getPaymentMethodType() );

        return creditCardVO;
    }

    @Override
    public CreditCardEntity voToEntity(CreditCardVO creditCardVO) {
        if ( creditCardVO == null ) {
            return null;
        }

        CreditCardEntity creditCardEntity = new CreditCardEntity();

        creditCardEntity.setId( creditCardVO.getId() );
        creditCardEntity.setDescription( creditCardVO.getDescription() );
        creditCardEntity.setPaymentMethodType( creditCardVO.getPaymentMethodType() );
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

    private Long creditCardEntityWalletEntityId(CreditCardEntity creditCardEntity) {
        if ( creditCardEntity == null ) {
            return null;
        }
        WalletEntity walletEntity = creditCardEntity.getWalletEntity();
        if ( walletEntity == null ) {
            return null;
        }
        Long id = walletEntity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
