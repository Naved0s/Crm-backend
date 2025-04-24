package com.crm.CrmSystem.repository;

import com.crm.CrmSystem.models.EmailModel;

// Interface
public interface EmailRepo {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailModel details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailModel details);
}
