package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.models.Leadsource;
import com.crm.CrmSystem.services.LeadService;
import com.crm.CrmSystem.services.LeadsourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads")
@CrossOrigin(origins = "*")
public class LeadController {
    @Autowired
    LeadService leadservice;

    @Autowired
    LeadsourceService leadsourceService ;

    @GetMapping("/all")
    public List<Leadsource> getall(){
       return leadsourceService.getall();
    }

    @GetMapping("/leadsall")
    public List<Lead> get(){
        return leadservice.get();
    }


    @PostMapping("/add/{leadId}")
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

    //leadsources,leads,qualified leads,new leads;
    @GetMapping("/sourceno")
    public Long noOfLeadsource(){
        return leadservice.noOfleadsource();
    }

    @GetMapping("/newleads")
    public int noOfNewLeads(){
        return leadservice.noOfNewLeads();
    }
}
