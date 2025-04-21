package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.LeadSource;
import com.crm.CrmSystem.repository.LeadSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadSourceService {

    @Autowired
    LeadSourceRepository leadSourceRepository;

    //ADDING THE LEAD SOURCE
    public LeadSource saveLeadSource(LeadSource leadSource){

        return leadSourceRepository.save(leadSource);
    }

    //FETCHING LEADSOURCE
    public List<LeadSource> getAllLeadSources() {
        return leadSourceRepository.findAll();
    }

    //FETCHING BY ID
    public LeadSource getLeadSourceById(int id) {
        return leadSourceRepository.findById(id).orElseThrow(() -> new RuntimeException("LeadSource not found"));
    }

    //DELETE BY ID
    public void deleteLeadSource(int id) {
        leadSourceRepository.deleteById(id);
    }

}
