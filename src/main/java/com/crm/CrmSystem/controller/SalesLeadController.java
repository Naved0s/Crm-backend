package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.SalesLead;
import com.crm.CrmSystem.services.SalesLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/salesLeads")
@CrossOrigin(origins = "http://localhost:4200")
public class SalesLeadController {
    @Autowired
    private SalesLeadService salesLeadService;

    // Endpoint to get all qualified sales leads
    @GetMapping("/qualified")
    public List<SalesLead> getQualifiedSalesLeads() {
        return salesLeadService.getall();
    }
}
