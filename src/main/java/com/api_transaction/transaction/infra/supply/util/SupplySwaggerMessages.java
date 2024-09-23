package com.api_transaction.transaction.infra.supply.util;

public class SupplySwaggerMessages {
    private SupplySwaggerMessages(){
        throw new AssertionError();
    }

    public static final String INCREASE_SUPPLY_REQUEST_EXAMPLE =
            "{ \"product\": \"1\", " +
            "\"amount\": \"2\"}";
}
