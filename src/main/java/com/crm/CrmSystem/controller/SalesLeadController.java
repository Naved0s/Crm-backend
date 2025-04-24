package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.EmailModel;
import com.crm.CrmSystem.models.SalesLead;
import com.crm.CrmSystem.services.EmailService;
import com.crm.CrmSystem.services.SalesLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesLeads")
@CrossOrigin(origins = "http://localhost:4200")
public class SalesLeadController {
    @Autowired
    private SalesLeadService salesLeadService;

    @Autowired
    EmailService emailService;

    // Endpoint to get all qualified sales leads
    @GetMapping("/qualified")
    public List<SalesLead> getQualifiedSalesLeads() {
        return salesLeadService.getall();
    }

    @PostMapping("/sendmail")
    public String sendMail(@RequestBody EmailModel emailModel){

        String status
                = emailService.sendSimpleMail(emailModel);
        System.out.println(status);
        return status;

    }
}
