package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.Leadsource;
import com.crm.CrmSystem.services.LeadsourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leadsource")
@CrossOrigin(origins = "http://localhost:4200")
public class LeadsourceController {

    @Autowired
    LeadsourceService leadsourceService;


    @PostMapping("/add")
    public Leadsource addlead(@RequestBody Leadsource leadsource){
        return leadsourceService.addLeadsource(leadsource);
    }

    @GetMapping("/all")
    public List<Leadsource> getall(){
        return leadsourceService.getall();
    }
}
