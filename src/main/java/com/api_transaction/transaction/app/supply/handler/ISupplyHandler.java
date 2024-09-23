package com.api_transaction.transaction.app.supply.handler;

import com.api_transaction.transaction.app.supply.dto.RestockDateResponse;
import com.api_transaction.transaction.app.supply.dto.SupplyRequest;

public interface ISupplyHandler {
    void createSupply(SupplyRequest supplyRequest, String token);
    RestockDateResponse getRestockDate(Long productId);
}
