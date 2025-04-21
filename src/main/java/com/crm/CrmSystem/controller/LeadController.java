package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.LeadSource;
import com.crm.CrmSystem.services.LeadSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leadsource")
@CrossOrigin(origins = "http://localhost:4200")
public class LeadController {

    @Autowired
    LeadSourceService leadSourceService;

    //GET ALL LEADSOURCE
    @GetMapping("/all")
    public List<LeadSource> getAll(){
        System.out.println("Getting all"    );
    return leadSourceService.getAllLeadSources();
    }

    //Add leadSource
    @PostMapping("/addlead")
    public LeadSource addLead(@RequestBody LeadSource leadSource){
        System.out.println("added : "+ leadSource);
        return leadSourceService.saveLeadSource(leadSource);
    }

}
