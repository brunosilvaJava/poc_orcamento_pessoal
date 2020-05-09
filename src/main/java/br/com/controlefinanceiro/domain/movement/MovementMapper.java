package br.com.controlefinanceiro.domain.movement;

import br.com.controlefinanceiro.domain.creditCard.InvoiceCardVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    @Mapping(source = "operation.paymentMethod.description", target = "creditCard")
    InvoiceCardVO entityToEnvoiceVO(MovementEntity movementEntity);

    List<InvoiceCardVO> entitysToEnvoicesVO(List<MovementEntity> creditCardEntityList);

}
