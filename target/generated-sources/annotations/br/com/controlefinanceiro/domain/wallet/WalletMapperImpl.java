package br.com.controlefinanceiro.domain.wallet;

import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-13T18:13:20-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class WalletMapperImpl implements WalletMapper {

    @Override
    public WalletVO entityToVO(WalletEntity entity) {
        if ( entity == null ) {
            return null;
        }

        WalletVO walletVO = new WalletVO();

        walletVO.setId( entity.getId() );
        walletVO.setDescription( entity.getDescription() );
        walletVO.setOverdraft( entity.getOverdraft() );

        return walletVO;
    }

    @Override
    public WalletEntity VOtoEntity(WalletVO vo) {
        if ( vo == null ) {
            return null;
        }

        WalletEntity walletEntity = new WalletEntity();

        walletEntity.setId( vo.getId() );
        walletEntity.setDescription( vo.getDescription() );
        walletEntity.setOverdraft( vo.getOverdraft() );

        return walletEntity;
    }
}
