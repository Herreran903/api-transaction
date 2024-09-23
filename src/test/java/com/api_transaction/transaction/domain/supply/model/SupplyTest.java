package com.api_transaction.transaction.domain.supply.model;

import org.junit.jupiter.api.Test;

import static com.api_transaction.transaction.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class SupplyTest {

    @Test
    void shouldUpdateSupplySuccessfully(){
        Supply supply = new Supply(null, null, 0, null, null,null);

        supply.setId(VALID_SUPPLY_ID);
        supply.setProduct(VALID_PRODUCT_ID);
        supply.setAmount(VALID_AMOUNT);
        supply.setDate(VALID_DATE);
        supply.setUser(VALID_USER_ID);
        supply.setRestockDate(VALID_RESTORE_DATE);

        assertEquals(VALID_SUPPLY_ID, supply.getId());
        assertEquals(VALID_PRODUCT_ID, supply.getProduct());
        assertEquals(VALID_AMOUNT, supply.getAmount());
        assertEquals(VALID_DATE, supply.getDate());
        assertEquals(VALID_USER_ID, supply.getUser());
        assertEquals(VALID_RESTORE_DATE, supply.getRestockDate());
    }

}