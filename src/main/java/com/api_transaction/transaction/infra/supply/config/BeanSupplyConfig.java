package com.api_transaction.transaction.infra.supply.config;

import com.api_transaction.transaction.domain.supply.api.ISupplyServicePort;
import com.api_transaction.transaction.domain.supply.spi.IFeignAdapterPort;
import com.api_transaction.transaction.domain.supply.spi.IJwtAdapterPort;
import com.api_transaction.transaction.domain.supply.spi.ISupplyPersistencePort;
import com.api_transaction.transaction.domain.supply.usecase.SupplyUseCase;
import com.api_transaction.transaction.infra.supply.out.ISupplyMapper;
import com.api_transaction.transaction.infra.supply.out.ISupplyRepository;
import com.api_transaction.transaction.infra.supply.out.SupplyAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanSupplyConfig {
    private final ISupplyRepository supplyRepository;
    private final ISupplyMapper supplyMapper;
    private final IJwtAdapterPort jwtAdapterPort;
    private final IFeignAdapterPort feignAdapterPort;

    @Bean
    public ISupplyPersistencePort supplyPersistence() {
        return new SupplyAdapter(supplyRepository, supplyMapper);
    }

    @Bean
    public ISupplyServicePort supplyService() {
        return new SupplyUseCase(supplyPersistence(), jwtAdapterPort, feignAdapterPort);
    }
}
