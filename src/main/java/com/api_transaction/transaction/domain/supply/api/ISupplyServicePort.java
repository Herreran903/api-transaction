package com.api_transaction.transaction.domain.supply.api;

import com.api_transaction.transaction.domain.supply.model.Supply;

public interface ISupplyServicePort {
    void createSupply(Supply supply, String token);
}
