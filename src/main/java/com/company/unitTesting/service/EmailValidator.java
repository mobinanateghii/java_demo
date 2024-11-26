package com.company.unitTesting.service;

import com.company.unitTesting.dto.EmailDto;

public class EmailValidator {
    public void validate(EmailDto email) {
        if (email.getAddress() == null) {
            throw new IllegalArgumentException();
        }
    }
}
