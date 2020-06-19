package br.com.controlefinanceiro.domain.recurrent;

import br.com.controlefinanceiro.domain.operation.PaymentRecurrentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RecurrentMapper {

    RecurrentMapper INSTANCE = Mappers.getMapper(RecurrentMapper.class);

    @Mapping(source = "days", target = "days", qualifiedByName = "daysStringToList")
    RecurrentEntity voToEntity(PaymentRecurrentVO paymentRecurrentVO);

    @Mapping(source = "days", target = "days", qualifiedByName = "daysListToString")
    @Mapping(source = "paymentMethod.id", target = "idPaymentMethod")
    @Mapping(source = "wallet.id", target = "idWallet")
    PaymentRecurrentVO entityToVo(RecurrentEntity recurrentEntity);

    List<PaymentRecurrentVO> entitiesToVos(List<RecurrentEntity> recurrentEntities);

    @Named("daysStringToList")
    default List<Integer> getDays(String days){
        return RecurrentEntity.stringToList(days);
    }

    @Named("daysListToString")
    default String setDays(List<Integer> days){
        return RecurrentEntity.listToString(days);
    }

}
