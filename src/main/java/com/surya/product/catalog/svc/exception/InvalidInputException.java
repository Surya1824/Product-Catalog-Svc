package com.surya.product.catalog.svc.exception;

public class InvalidInputException extends Throwable {
    public InvalidInputException(String notValidProductType) {
        super(notValidProductType);
    }
}
