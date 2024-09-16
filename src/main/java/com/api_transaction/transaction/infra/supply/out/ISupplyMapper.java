package com.api_transaction.transaction.infra.supply.out;

import com.api_transaction.transaction.domain.supply.model.Supply;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyMapper {
    SupplyEntity toEntity(Supply supply);
}
