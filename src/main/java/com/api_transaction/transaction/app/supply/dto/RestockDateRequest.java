package com.api_transaction.transaction.app.supply.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.api_transaction.transaction.domain.supply.exception.SupplyExceptionMessage.EMPTY_PRODUCT;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestockDateRequest {
    @NotNull(message = EMPTY_PRODUCT)
    private Long product;
}
