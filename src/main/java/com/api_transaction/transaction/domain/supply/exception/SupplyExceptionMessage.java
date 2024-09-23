package com.api_transaction.transaction.domain.supply.exception;

import static com.api_transaction.transaction.domain.supply.util.SupplyConstants.MIN_AMOUNT_VALUE;

public class SupplyExceptionMessage {
    private SupplyExceptionMessage(){
        throw new AssertionError();
    }

    public static final String EMPTY_PRODUCT =
            "The 'product' field is empty";

    public static final String EMPTY_AMOUNT =
            "The 'amount' field is empty";

    public static final String MIN_AMOUNT =
            "The 'amount' field must be greater than " + MIN_AMOUNT_VALUE;

    public static final String NO_FOUND_SUPPLY =
            "Supply does not found";

}
