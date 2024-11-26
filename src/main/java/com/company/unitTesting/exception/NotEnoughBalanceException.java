package com.company.unitTesting.exception;

public class NotEnoughBalanceException extends Exception {

    public NotEnoughBalanceException() {
    }

    public NotEnoughBalanceException(String error) {
        super(error);
    }
}
