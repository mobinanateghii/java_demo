package com.company.unitTesting.service;

import com.company.unitTesting.dto.EmailDto;
import com.company.unitTesting.dto.EmailTemplateDto;
import com.company.unitTesting.dto.UserDto;

public class    EmailMessenger {
    private EmailTemplateEngine templateEngine;
    private MailServer mailServer;

    public void sendMessage(UserDto userDto, EmailTemplateDto template) {
        String msgContent = templateEngine.prepareMessage(userDto, template);
        EmailDto email = EmailDto.builder()
                .message(msgContent)
                .address(userDto.getEmail())
                .build();
        mailServer.send(email);
    }
}
