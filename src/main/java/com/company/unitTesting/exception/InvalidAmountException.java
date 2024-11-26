package com.company.unitTesting.exception;

public class InvalidAmountException extends Exception {

    public InvalidAmountException() {
    }

    public InvalidAmountException(String error) {
        super(error);
    }
}
