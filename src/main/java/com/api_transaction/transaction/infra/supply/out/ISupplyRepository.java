package com.api_transaction.transaction.infra.supply.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {
    Optional<SupplyEntity> findByProduct(Long productId);
}
