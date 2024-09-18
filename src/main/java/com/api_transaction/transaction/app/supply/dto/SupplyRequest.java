package com.api_transaction.transaction.app.supply.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.api_transaction.transaction.domain.supply.exception.SupplyExceptionMessage.*;
import static com.api_transaction.transaction.domain.supply.util.SupplyConstants.MIN_AMOUNT_VALUE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplyRequest {
    @NotNull(message = EMPTY_PRODUCT)
    private Long product;

    @NotNull(message = EMPTY_AMOUNT)
    @Min(value = MIN_AMOUNT_VALUE, message = MIN_AMOUNT)
    private int amount;
}
