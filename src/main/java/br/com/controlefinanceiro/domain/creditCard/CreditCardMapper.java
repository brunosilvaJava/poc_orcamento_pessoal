package br.com.controlefinanceiro.domain.creditCard;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCardVO entityToVO(CreditCardEntity creditCardEntity);

    CreditCardEntity voToEntity(CreditCardVO creditCardVO);

    List<CreditCardVO> entitysToVOs(List<CreditCardEntity> creditCardEntityList);

}
