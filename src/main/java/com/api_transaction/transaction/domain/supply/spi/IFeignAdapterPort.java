package com.api_transaction.transaction.domain.supply.spi;

public interface IFeignAdapterPort {
    void updateStock(Long productId, int amount);
}
