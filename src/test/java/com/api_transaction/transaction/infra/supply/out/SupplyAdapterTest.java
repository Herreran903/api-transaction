package com.api_transaction.transaction.infra.supply.out;

import com.api_transaction.transaction.domain.supply.model.Supply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.api_transaction.transaction.utils.TestConstants.*;
import static org.mockito.Mockito.*;

class SupplyAdapterTest {

    @Mock
    private ISupplyRepository supplyRepository;

    @Mock
    private ISupplyMapper supplyMapper;

    @InjectMocks
    private SupplyAdapter supplyAdapter;

    private Supply supply;
    private SupplyEntity supplyEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        supply = new Supply(VALID_SUPPLY_ID, VALID_PRODUCT_ID, VALID_AMOUNT, VALID_DATE, VALID_USER_ID);
        supplyEntity = new SupplyEntity(VALID_SUPPLY_ID, VALID_PRODUCT_ID, VALID_AMOUNT, VALID_DATE, VALID_USER_ID);
    }

    @Test
    void shouldCreateSupplySuccessfully(){
        when(supplyMapper.toEntity(supply)).thenReturn(supplyEntity);

        supplyAdapter.createSupply(supply);

        verify(supplyMapper, times(1)).toEntity(supply);
        verify(supplyRepository, times(1)).save(supplyEntity);
    }

}