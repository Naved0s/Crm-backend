package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.EmailModel;
import com.crm.CrmSystem.models.SalesLead;
import com.crm.CrmSystem.models.enums.SalesLeadStatus;
import com.crm.CrmSystem.repository.SalesLeadRepository;
import com.crm.CrmSystem.services.EmailService;
import com.crm.CrmSystem.services.SalesLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/salesLeads")
@CrossOrigin(origins = "http://localhost:4200")
public class SalesLeadController {
    @Autowired
    private SalesLeadService salesLeadService;

    @Autowired
    private SalesLeadRepository salesLeadRepository;

    @Autowired
    EmailService emailService;

    // Endpoint to get all qualified sales leads
    @GetMapping("/qualified")
    public List<SalesLead> getQualifiedSalesLeads() {
        return salesLeadService.getall();
    }

    @PostMapping("/sendmail/{id}")
    public String sendMail(@RequestBody EmailModel emailModel, @PathVariable int id) {
        System.out.println(id);  // To check if the id is being captured correctly

        SalesLead l = salesLeadService.findbyId(id).get();  // Fetch SalesLead using the provided id
        System.out.println(l.getLead().getLeadsource().getLeadName());  // Print lead name for debugging

        // Set the email recipient and other values
        emailModel.setRecipient(l.getLead().getLeadsource().getLeadEmail());
        System.out.println(l.getLead().getLeadsource().getLeadEmail());
        l.setProposedValue(emailModel.getDealValue());
        l.setLeadStatus(SalesLeadStatus.NEGOTIATED);
        l.setProposedDate(LocalDateTime.now());
        salesLeadRepository.save(l);

        // Send the email
        String status = emailService.sendSimpleMail(emailModel);
        System.out.println(status);
        System.out.println(l.getLeadStatus() + " " + l.getProposedValue());

        return status;
    }

}
