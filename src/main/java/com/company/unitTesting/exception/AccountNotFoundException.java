package com.company.unitTesting.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String error) {
        super(error);
    }
}
