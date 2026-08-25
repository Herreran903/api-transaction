package com.api_transaction.transaction.infra.feign;

import com.api_transaction.transaction.app.supply.dto.StockRequest;
import com.api_transaction.transaction.domain.supply.spi.IFeignAdapterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockFeignAdapter implements IFeignAdapterPort {
    private final StockFeignClient stockFeignClient;

    @Override
    public void updateStock(Long productId, int amount) {
        StockRequest stockRequest = new StockRequest(productId, amount);
        stockFeignClient.updateStock(stockRequest);
    }
}
