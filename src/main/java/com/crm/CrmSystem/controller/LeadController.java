package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.repository.LeadRepository;
import com.crm.CrmSystem.services.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/lead")
@CrossOrigin(origins = "*")
public class LeadController {

    @Autowired
    LeadService leadService;

    @PostMapping("/addlead")
    public Lead addlead(@RequestBody Lead lead){
     //   lead.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return leadService.addlead(lead);
    }

    @GetMapping("/getall")
    public List<Lead> getall(){
        return leadService.getAllLead();
    }

//    @PutMapping("/update")
//    public void updateLead(@RequestParam String leadStatus, @RequestParam int id){
//        leadService.updateLead(leadStatus,id);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLead(@PathVariable int id, @RequestBody String leadStatus) {

            leadService.updateLead(id, leadStatus);
            System.out.println("This is latest status:"+leadStatus);
            return ResponseEntity.ok("Updated lead status to " + leadStatus);

    }


}
