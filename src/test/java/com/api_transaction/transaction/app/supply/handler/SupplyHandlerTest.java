package com.api_transaction.transaction.app.supply.handler;

import com.api_transaction.transaction.app.supply.dto.SupplyRequest;
import com.api_transaction.transaction.app.supply.mapper.ISupplyRequestMapper;
import com.api_transaction.transaction.domain.supply.api.ISupplyServicePort;
import com.api_transaction.transaction.domain.supply.model.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.api_transaction.transaction.utils.TestConstants.*;
import static org.mockito.Mockito.*;

class SupplyHandlerTest {

    @Mock
    private ISupplyServicePort supplyServicePort;

    @Mock
    private ISupplyRequestMapper supplyRequestMapper;

    @InjectMocks
    private SupplyHandler supplyHandler;

    private Supply supply;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        supply = new Supply(VALID_SUPPLY_ID, VALID_PRODUCT_ID, VALID_AMOUNT, VALID_DATE, VALID_USER_ID);
    }

    @Test
    void shouldCallUpdateStockOnProductServicePort(){
        SupplyRequest supplyRequest = new SupplyRequest(VALID_PRODUCT_ID, VALID_AMOUNT);

        when(supplyRequestMapper.toSupply(supplyRequest)).thenReturn(supply);

        supplyHandler.createSupply(supplyRequest, VALID_TOKEN);

        verify(supplyServicePort, times(1)).createSupply(supply, VALID_TOKEN);
    }
  
}