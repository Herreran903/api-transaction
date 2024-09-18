package com.api_transaction.transaction.app.supply.handler;

import com.api_transaction.transaction.app.supply.dto.SupplyRequest;
import com.api_transaction.transaction.app.supply.mapper.ISupplyRequestMapper;
import com.api_transaction.transaction.domain.supply.api.ISupplyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplyHandler implements ISupplyHandler {
    private final ISupplyServicePort supplyServicePort;
    private final ISupplyRequestMapper supplyRequestMapper;

    @Override
    public void createSupply(SupplyRequest supplyRequest, String token) {
        supplyServicePort.createSupply(supplyRequestMapper.toSupply(supplyRequest), token);
    }
}
