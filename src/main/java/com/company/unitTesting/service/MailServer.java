package com.company.unitTesting.service;

import com.company.unitTesting.dto.EmailDto;


public class MailServer {
    private EmailValidator emailValidator;

    public void send(EmailDto email){
        emailValidator.validate(email);
        /*sending email to address*/
    }
}
