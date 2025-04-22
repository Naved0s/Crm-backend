package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.CrmService;
import com.crm.CrmSystem.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceController {

    @Autowired
    ServiceRepository serviceRepository;

    @PostMapping("/addservice")
    public CrmService addService(@RequestBody CrmService crmService){
        return serviceRepository.save(crmService);
    }

    @GetMapping("/all")
    public List<CrmService> getall(){
        return serviceRepository.findAll();
    }

}
