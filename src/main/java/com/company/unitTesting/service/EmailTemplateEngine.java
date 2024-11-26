package com.company.unitTesting.service;

import com.company.unitTesting.dto.EmailTemplateDto;
import com.company.unitTesting.dto.UserDto;

public class EmailTemplateEngine {

    public String prepareMessage(UserDto user, EmailTemplateDto template) {
        // get necessary info from a template
        // paste necessary info from a client into a template
        return "Some template";
    }

    public boolean evaluateTemplate(EmailTemplateDto template) {
        return false;
    }
}
