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
       //return leadsourceService.getall();
        return  leadservice.getAvailableLeadSources();
    }

    @GetMapping("/leadsall")
    public List<Lead> get(){
        return leadservice.get();
    }


    @PostMapping("/add/{leadId}")
    public ResponseEntity<String> addLead(@PathVariable int leadId) {
        try {
            leadservice.addleads(leadId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

        @PutMapping("/edit/{leadId}")
        public ResponseEntity<String> changeStatus(@PathVariable int leadId,@RequestParam String status){
            leadservice.changeStatus(leadId,status);
            return ResponseEntity.ok().build();
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
