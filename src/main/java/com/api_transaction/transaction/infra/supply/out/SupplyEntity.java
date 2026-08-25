package com.api_transaction.transaction.infra.supply.out;

import com.api_transaction.transaction.domain.supply.util.SupplyConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = SupplyConstants.SUPPLY_TABLE_NAME)
public class SupplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long product;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private LocalDate restockDate;

    @Column(nullable = false)
    private Long user;
}
