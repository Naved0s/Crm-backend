package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.models.SalesLead;
import com.crm.CrmSystem.models.enums.LeadStatus;
import com.crm.CrmSystem.models.enums.SalesLeadStatus;
import com.crm.CrmSystem.repository.LeadRepository;
import com.crm.CrmSystem.repository.SalesLeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesLeadService {

    @Autowired
    SalesLeadRepository salesLeadRepository;

    @Autowired
    LeadRepository leadRepository;

    public List<SalesLead> getall() {
        return salesLeadRepository.findAll();
    }
//    public void moveQualifiedLeadsToSales() {
//        List<Lead> qualifiedLeads = leadRepository.findByLeadStatus(LeadStatus.QUALIFIED);
//
//        for (Lead lead : qualifiedLeads) {
//            SalesLead salesLead = new SalesLead();
//            salesLead.setLead(lead);
//           // salesLead.setLeadStatus(SalesLeadStatus.NEW_LEAD); // Start as NEW_LEAD in sales pipeline
//           // System.out.println("Added this lead to sales lead: "+ salesLead.getLeadStatus());
//            salesLeadRepository.save(salesLead);
//        }
//    }

    public Optional<SalesLead> findbyId(int id){
        return salesLeadRepository.findById(id);
    }

    public boolean existsInSalesLead(Lead lead) {
        return salesLeadRepository.existsByLead(lead);
    }

    public void moveSingleLeadToSales(Lead lead) {
        SalesLead salesLead = new SalesLead();
        salesLead.setDealStatus(SalesLeadStatus.NEW_LEAD);
        salesLead.setLead(lead);
       // salesLead.setLeadStatus(SalesLeadStatus.NEW_LEAD);
        salesLeadRepository.save(salesLead);
        System.out.println("✔ Added to SalesLead: " + lead.getLeadId());
    }

    public void update(SalesLead lead){
        salesLeadRepository.save(lead);

    }

}
