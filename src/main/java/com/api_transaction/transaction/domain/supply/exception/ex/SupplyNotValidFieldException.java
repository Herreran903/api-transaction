package com.api_transaction.transaction.domain.supply.exception.ex;

import com.api_transaction.transaction.domain.error.ErrorDetail;

import java.io.Serializable;
import java.util.List;

public class SupplyNotValidFieldException extends RuntimeException implements Serializable {
    private final transient List<ErrorDetail> errors;

    public SupplyNotValidFieldException(String message, List<ErrorDetail> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }
}
