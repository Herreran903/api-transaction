package com.api_transaction.transaction.domain.supply.spi;

import com.api_transaction.transaction.domain.supply.model.Supply;

public interface ISupplyPersistencePort {
    void createSupply(Supply supply);
}
