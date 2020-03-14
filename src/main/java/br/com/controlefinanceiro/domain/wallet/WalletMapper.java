package br.com.controlefinanceiro.domain.wallet;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WalletMapper {

    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    WalletVO entityToVO(WalletEntity entity);

    WalletEntity VOtoEntity(WalletVO vo);

    List<WalletVO> entitiesToVOs(List<WalletEntity> entities);

}
