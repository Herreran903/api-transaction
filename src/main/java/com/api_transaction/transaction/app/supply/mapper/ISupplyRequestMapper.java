package com.api_transaction.transaction.app.supply.mapper;

import com.api_transaction.transaction.app.supply.dto.SupplyRequest;
import com.api_transaction.transaction.domain.supply.model.Supply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.api_transaction.transaction.domain.supply.util.SupplyConstants.*;

@Mapper(componentModel = "spring")
public interface ISupplyRequestMapper {
    @Mapping(target = SUPPLY_ID, ignore = true)
    @Mapping(target = SUPPLY_DATE, ignore = true)
    @Mapping(target = SUPPLY_USER, ignore = true)
    Supply toSupply(SupplyRequest supplyRequest);
}
