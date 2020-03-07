package br.com.controlefinanceiro.domain.wallet;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    WalletVO entityToVO(WalletEntity entity);

    WalletEntity VOtoEntity(WalletVO vo);

}
