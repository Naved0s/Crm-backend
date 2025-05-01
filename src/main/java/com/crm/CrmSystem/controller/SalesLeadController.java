package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.EmailModel;
import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.models.SalesLead;
import com.crm.CrmSystem.models.enums.LeadStatus;
import com.crm.CrmSystem.models.enums.SalesLeadStatus;
import com.crm.CrmSystem.repository.SalesLeadRepository;
import com.crm.CrmSystem.services.ClientService;
import com.crm.CrmSystem.services.EmailService;
import com.crm.CrmSystem.services.SalesLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/salesLeads")
@CrossOrigin(origins = "http://localhost:4200")
public class SalesLeadController {
    @Autowired
    private SalesLeadService salesLeadService;

    @Autowired
    private SalesLeadRepository salesLeadRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    EmailService emailService;

    // Endpoint to get all qualified sales leads
    @GetMapping("/qualified")
    public List<SalesLead> getQualifiedSalesLeads() {
        return salesLeadService.getall().stream().filter(
                salesLead -> salesLead.getLead().getLeadsource().isActive() &
                        ( salesLead.getDealStatus() == SalesLeadStatus.NEW_LEAD ||
                            salesLead.getDealStatus() == SalesLeadStatus.PROPOSED)

        ).toList() ;
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
       l.setDealStatus(SalesLeadStatus.PROPOSED);
        l.setProposedDate(emailModel.getProposedTime());
        salesLeadRepository.save(l);

        // Send the email
        String status = emailService.sendSimpleMail(emailModel);
        System.out.println(status);
      //  System.out.println(l.getLeadStatus() + " " + l.getProposedValue());

        return status;
    }

    @GetMapping("/getNego")
    public List<SalesLead> getNegotiations() {
        return salesLeadService.getall().stream().filter(
                salesLead -> !salesLead.getDealStatus().equals(SalesLeadStatus.NEW_LEAD) & salesLead.getLead().getLeadsource().isActive()
        ).toList();        // Compare directly with the enum
                //.collect(Collectors.toList());  // Use collect() if you're on Java 8 or earlier
    }

    @PutMapping("/updateNego/{id}")
    public ResponseEntity<String> update(@RequestBody SalesLead salesLead, @PathVariable int id) {
        // Fetch the existing SalesLead from the database by id
        Optional<SalesLead> existingSalesLead = salesLeadService.findbyId(id);

        if (existingSalesLead.isPresent()) {
            SalesLead l1 = existingSalesLead.get();

            // Update fields
            //deal name //
            l1.setDealName(salesLead.getDealName());
            l1.setClosedDate(salesLead.getClosedDate());
            l1.setClosedValue(salesLead.getClosedValue());
            l1.setDealStatus(salesLead.getDealStatus());
            System.out.println(salesLead.getClosedValue());
           // System.out.println(salesLead.getLead().getLeadStatus());

            // Call service to save the updated SalesLead
            salesLeadService.update(l1);

            return ResponseEntity.ok("Sales Lead updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sales Lead not found!");
        }
    }

    @GetMapping("/wins")
    public List<SalesLead> getDeals(){
        return salesLeadService.getall().stream().filter(
                salesLead -> salesLead.getDealStatus().equals(SalesLeadStatus.WON) & salesLead.getLead().getLeadsource().isActive()
        ).toList();
    }

    @PostMapping("/sendCredentials/{id}")
    public ResponseEntity<Map<String, String>> sendCredentials(@PathVariable int id) {
        Map<String, String> response = new HashMap<>();
        try {
            SalesLead s1 = salesLeadService.findbyId(id)
                    .orElseThrow(() -> new RuntimeException("SalesLead not found for ID: " + id));

            String email = s1.getLead().getLeadsource().getLeadEmail();
            String body = "Hi client, your login credentials are:\nEmail: " + email + "\nPassword: 123";
            String subject = "Welcome! Your Login Credentials";

            boolean created = clientService.sendCredentials(id);
            if (created) {
                emailService.sendSimpleMail(new EmailModel(email, body, subject));
                response.put("message", "Email sent to client with ID: " + id);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Client already exists. Credentials were not resent.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }




    @PostMapping("/setNego/{id}")
    public void setNegotiation(@RequestBody SalesLead lead,@PathVariable int id){
        System.out.println(id);
        SalesLead l1 = salesLeadService.findbyId(id).get();
        l1.setClosedDate(lead.getClosedDate());
        l1.setClosedValue(lead.getClosedValue());
        l1.getLead().setLeadStatus(lead.getLead().getLeadStatus());
        salesLeadService.editLeadsource(lead);

    }


}
