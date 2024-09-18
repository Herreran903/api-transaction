package com.api_transaction.transaction.domain.supply.util;

public class SupplyConstants {
    private SupplyConstants(){
        throw new AssertionError();
    }

    public static final int MIN_AMOUNT_VALUE = 1;

    public static final String SUPPLY_ID = "id";
    public static final String SUPPLY_PRODUCT = "product";
    public static final String SUPPLY_USER = "user";
    public static final String SUPPLY_AMOUNT = "amount";
    public static final String SUPPLY_DATE = "date";

    public static final String SUPPLY_TABLE_NAME = "supplies";
}
