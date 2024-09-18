package com.api_transaction.transaction.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class TestConstants {

    private TestConstants() {
        throw new AssertionError();
    }

    public static final Long VALID_SUPPLY_ID = 1L;
    public static final Long VALID_PRODUCT_ID = 1L;
    public static final int VALID_AMOUNT = 10;
    public static final LocalDateTime VALID_DATE = LocalDateTime.now();
    public static final Long VALID_USER_ID = 1L;
    public static final String VALID_TOKEN = "VALID_TOKEN";
}
