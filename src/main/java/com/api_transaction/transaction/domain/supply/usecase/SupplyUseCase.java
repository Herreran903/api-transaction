package com.api_transaction.transaction.domain.supply.usecase;

import com.api_transaction.transaction.domain.error.ErrorDetail;
import com.api_transaction.transaction.domain.supply.api.ISupplyServicePort;
import com.api_transaction.transaction.domain.supply.exception.ex.SupplyNotValidFieldException;
import com.api_transaction.transaction.domain.supply.exception.ex.UserInvalidException;
import com.api_transaction.transaction.domain.supply.model.Supply;
import com.api_transaction.transaction.domain.supply.spi.IFeignAdapterPort;
import com.api_transaction.transaction.domain.supply.spi.IJwtAdapterPort;
import com.api_transaction.transaction.domain.supply.spi.ISupplyPersistencePort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.api_transaction.transaction.domain.supply.exception.SupplyExceptionMessage.EMPTY_PRODUCT;
import static com.api_transaction.transaction.domain.supply.exception.SupplyExceptionMessage.MIN_AMOUNT;
import static com.api_transaction.transaction.domain.supply.util.SupplyConstants.*;
import static com.api_transaction.transaction.domain.util.GlobalExceptionMessage.INVALID_OBJECT;
import static com.api_transaction.transaction.domain.util.GlobalExceptionMessage.INVALID_USER;

public class SupplyUseCase implements ISupplyServicePort {
    private final ISupplyPersistencePort supplyPersistencePort;
    private final IJwtAdapterPort jwtAdapterPort;
    private final IFeignAdapterPort feignAdapterPort;

    public SupplyUseCase(ISupplyPersistencePort supplyPersistencePort, IJwtAdapterPort jwtAdapterPort, IFeignAdapterPort feignAdapterPort) {
        this.supplyPersistencePort = supplyPersistencePort;
        this.jwtAdapterPort = jwtAdapterPort;
        this.feignAdapterPort = feignAdapterPort;
    }

    private void validateSupply(Supply supply) {
        List<ErrorDetail> errors = new ArrayList<>();

        if (supply.getProduct() == null)
            errors.add(new ErrorDetail(SUPPLY_PRODUCT, EMPTY_PRODUCT));

        if (supply.getAmount() < MIN_AMOUNT_VALUE)
            errors.add(new ErrorDetail(SUPPLY_AMOUNT, MIN_AMOUNT));

        if (!errors.isEmpty())
            throw new SupplyNotValidFieldException(INVALID_OBJECT, errors);
    }

    private Long getUserIdFromToken(String token) {
        Long userId = jwtAdapterPort.getUserId(token);
        if (userId == null)
            throw new UserInvalidException(INVALID_USER);

        return userId;
    }

    @Override
    public void createSupply(Supply supply, String token) {
        validateSupply(supply);

        Long userId = getUserIdFromToken(token);

        supply.setUser(userId);
        supply.setDate(LocalDateTime.now());

        feignAdapterPort.updateStock(supply.getProduct(), supply.getAmount());

        try {
            supplyPersistencePort.createSupply(supply);
        } catch (Exception e) {
            feignAdapterPort.updateStock(supply.getProduct(), -supply.getAmount());
            throw e;
        }
    }
}
