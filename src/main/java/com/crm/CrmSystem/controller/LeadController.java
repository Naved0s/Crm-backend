package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.services.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leads")
@CrossOrigin(origins = "*")
public class LeadController {
    @Autowired
    LeadService leadservice;

    @GetMapping("/add/{leadId}")
    public ResponseEntity<String> addLead(@PathVariable int leadId) {
        try {
            leadservice.addleads(leadId);
            return ResponseEntity.ok("Lead added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add lead: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{leadId}")
    public void changeStatus(@PathVariable int leadId,@RequestParam String status){

        leadservice.changeStatus(leadId,status);
    }
}
