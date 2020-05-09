package br.com.controlefinanceiro.domain.creditCard;

import br.com.controlefinanceiro.domain.movement.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    @Mapping(source = "description", target = "description")
    @Mapping(source = "walletEntity.id", target = "idWallet")
    CreditCardVO entityToVO(CreditCardEntity creditCardEntity);

    CreditCardEntity voToEntity(CreditCardVO creditCardVO);

    List<CreditCardVO> entitysToVOs(List<CreditCardEntity> creditCardEntityList);


}
