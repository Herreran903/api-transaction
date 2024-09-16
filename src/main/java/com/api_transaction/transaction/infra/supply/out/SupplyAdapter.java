package com.api_transaction.transaction.infra.supply.out;

import com.api_transaction.transaction.domain.supply.model.Supply;
import com.api_transaction.transaction.domain.supply.spi.ISupplyPersistencePort;

public class SupplyAdapter implements ISupplyPersistencePort {
    private final ISupplyRepository supplyRepository;
    private final ISupplyMapper supplyMapper;

    public SupplyAdapter(ISupplyRepository supplyRepository, ISupplyMapper supplyMapper) {
        this.supplyRepository = supplyRepository;
        this.supplyMapper = supplyMapper;
    }

    @Override
    public void createSupply(Supply supply) {
        supplyRepository.save(supplyMapper.toEntity(supply));
    }
}
