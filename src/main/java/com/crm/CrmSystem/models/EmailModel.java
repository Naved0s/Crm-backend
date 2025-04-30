package com.crm.CrmSystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {

    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
    private double dealValue;

    private String proposedTime;

    public EmailModel(String email, String body,String subject) {
        this.recipient = email;
        this.msgBody = body;
        this.subject = subject;
    }

    //proposed Value

    //male --> negotation  sales lead --> email -- >propoed -- > negoatioan --> praposed--> negotation
}

