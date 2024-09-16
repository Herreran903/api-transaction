package com.api_transaction.transaction.infra.supply.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplyRepository extends JpaRepository<SupplyEntity, Long> {

}
