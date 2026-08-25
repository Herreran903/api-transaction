package com.api_transaction.transaction.domain.supply.spi;

public interface IJwtAdapterPort {
    Long getUserId(String token);
}
