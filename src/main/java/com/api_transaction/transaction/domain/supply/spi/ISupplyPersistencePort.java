package com.api_transaction.transaction.domain.supply.spi;

import com.api_transaction.transaction.domain.supply.model.Supply;

import java.util.Optional;

public interface ISupplyPersistencePort {
    void createSupply(Supply supply);
    Optional<Supply> getSupplyByProductId(Long productId);
}
