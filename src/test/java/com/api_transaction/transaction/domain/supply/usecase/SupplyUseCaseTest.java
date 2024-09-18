package com.api_transaction.transaction.domain.supply.usecase;

import com.api_transaction.transaction.domain.supply.exception.ex.SupplyNotValidFieldException;
import com.api_transaction.transaction.domain.supply.exception.ex.UserInvalidException;
import com.api_transaction.transaction.domain.supply.model.Supply;
import com.api_transaction.transaction.domain.supply.spi.IFeignAdapterPort;
import com.api_transaction.transaction.domain.supply.spi.IJwtAdapterPort;
import com.api_transaction.transaction.domain.supply.spi.ISupplyPersistencePort;
import com.api_transaction.transaction.domain.util.GlobalExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.api_transaction.transaction.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplyUseCaseTest {

    @Mock
    private ISupplyPersistencePort supplyPersistencePort;

    @Mock
    private IJwtAdapterPort jwtAdapterPort;

    @Mock
    private IFeignAdapterPort feignAdapterPort;

    @InjectMocks
    private SupplyUseCase supplyUseCase;

    private Supply supply;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        supply = new Supply(VALID_SUPPLY_ID, VALID_PRODUCT_ID, VALID_AMOUNT, VALID_DATE, VALID_USER_ID);
    }

    @Test
    void shouldThrowExceptionWhenProductIsInvalid() {
        supply.setProduct(null);

        SupplyNotValidFieldException exception = assertThrows(SupplyNotValidFieldException.class, () -> {
            supplyUseCase.createSupply(supply, VALID_TOKEN);
        });

        assertEquals(GlobalExceptionMessage.INVALID_OBJECT, exception.getMessage());
        assertFalse(exception.getErrors().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenAmountIsInvalid() {
        supply.setAmount(0);

        SupplyNotValidFieldException exception = assertThrows(SupplyNotValidFieldException.class, () -> {
            supplyUseCase.createSupply(supply, VALID_TOKEN);
        });

        assertEquals(GlobalExceptionMessage.INVALID_OBJECT, exception.getMessage());
        assertFalse(exception.getErrors().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenUserIsInvalid() {
        when(jwtAdapterPort.getUserId(VALID_TOKEN)).thenReturn(null);

        UserInvalidException exception = assertThrows(UserInvalidException.class, () -> {
            supplyUseCase.createSupply(supply, VALID_TOKEN);
        });

        verify(jwtAdapterPort).getUserId(VALID_TOKEN);
        assertEquals(GlobalExceptionMessage.INVALID_USER, exception.getMessage());
        verify(feignAdapterPort, never()).updateStock(anyLong(), anyInt());
        verify(supplyPersistencePort, never()).createSupply(any(Supply.class));
    }

    @Test
    void shouldThrowExceptionWhenRevertStockUpdate() {
        when(jwtAdapterPort.getUserId(VALID_TOKEN)).thenReturn(VALID_USER_ID);

        doThrow(new RuntimeException("DB error")).when(supplyPersistencePort).createSupply(any(Supply.class));

        assertThrows(RuntimeException.class, () -> {
            supplyUseCase.createSupply(supply, VALID_TOKEN);
        });

        verify(feignAdapterPort).updateStock(supply.getProduct(), supply.getAmount());
        verify(feignAdapterPort).updateStock(supply.getProduct(), -supply.getAmount());
    }

    @Test
    void shouldCreateSupplySuccessfully() {
        when(jwtAdapterPort.getUserId(VALID_TOKEN)).thenReturn(VALID_USER_ID);

        supplyPersistencePort.createSupply(supply);

        verify(supplyPersistencePort, times(1)).createSupply(supply);
        assertEquals(VALID_USER_ID, supply.getUser());
        assertNotNull(supply.getDate());
    }


}