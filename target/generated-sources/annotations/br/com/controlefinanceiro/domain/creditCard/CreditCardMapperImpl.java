package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.creditCard.CreditCardEntity.CreditCardEntityBuilder;
import br.com.controlefinanceiro.domain.creditCard.CreditCardVO.CreditCardVOBuilder;
import br.com.controlefinanceiro.domain.paymentMethod.PaymentMethodEntity;
import br.com.controlefinanceiro.domain.wallet.WalletEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-26T17:40:56-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class CreditCardMapperImpl implements CreditCardMapper {

    @Override
    public CreditCardVO entityToVO(CreditCardEntity creditCardEntity) {
        if ( creditCardEntity == null ) {
            return null;
        }

        CreditCardVOBuilder creditCardVO = CreditCardVO.builder();

        creditCardVO.description( creditCardEntityPaymentMethodEntityDescription( creditCardEntity ) );
        creditCardVO.idWallet( creditCardEntityPaymentMethodEntityWalletEntityId( creditCardEntity ) );
        creditCardVO.id( creditCardEntity.getId() );
        creditCardVO.valueLimit( creditCardEntity.getValueLimit() );
        creditCardVO.dayClosingEnvoice( creditCardEntity.getDayClosingEnvoice() );
        creditCardVO.dayPay( creditCardEntity.getDayPay() );

        return creditCardVO.build();
    }

    @Override
    public CreditCardEntity voToEntity(CreditCardVO creditCardVO) {
        if ( creditCardVO == null ) {
            return null;
        }

        CreditCardEntityBuilder creditCardEntity = CreditCardEntity.builder();

        creditCardEntity.id( creditCardVO.getId() );
        creditCardEntity.valueLimit( creditCardVO.getValueLimit() );
        creditCardEntity.dayClosingEnvoice( creditCardVO.getDayClosingEnvoice() );
        creditCardEntity.dayPay( creditCardVO.getDayPay() );

        return creditCardEntity.build();
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

    private String creditCardEntityPaymentMethodEntityDescription(CreditCardEntity creditCardEntity) {
        if ( creditCardEntity == null ) {
            return null;
        }
        PaymentMethodEntity paymentMethodEntity = creditCardEntity.getPaymentMethodEntity();
        if ( paymentMethodEntity == null ) {
            return null;
        }
        String description = paymentMethodEntity.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }

    private Long creditCardEntityPaymentMethodEntityWalletEntityId(CreditCardEntity creditCardEntity) {
        if ( creditCardEntity == null ) {
            return null;
        }
        PaymentMethodEntity paymentMethodEntity = creditCardEntity.getPaymentMethodEntity();
        if ( paymentMethodEntity == null ) {
            return null;
        }
        WalletEntity walletEntity = paymentMethodEntity.getWalletEntity();
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
