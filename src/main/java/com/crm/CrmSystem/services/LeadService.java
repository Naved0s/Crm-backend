package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.models.LeadSource;
import com.crm.CrmSystem.models.enums.LeadStatus;
import com.crm.CrmSystem.repository.LeadRepository;
import com.crm.CrmSystem.repository.LeadSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LeadService {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    LeadSourceRepository leadSourceRepository;

    public Lead addlead(Lead lead){
        return leadRepository.save(lead);
    }

    public List<Lead> getAllLead(){
        return leadRepository.findAll();
    }

    public Lead updateLead(int id,String status){
    Optional<Lead> l = leadRepository.findById(id);
        if(l.isPresent()){
            String cleanedStatus = status.trim().toUpperCase();  // 🧼 normalize it
            LeadStatus leadStatus = LeadStatus.valueOf(cleanedStatus);
            Lead lead = l.get();
            System.out.println(status.toUpperCase());
            lead.setLeadStatus(leadStatus);

            return  leadRepository.save(lead);

        }
        return null;

  //  l.ifPresent(lead -> lead.setLeadStatus(LeadStatus.valueOf(status)));

    }
    public Lead createLead(Integer leadId){
        Optional<Lead> leadSource= leadRepository.findById(leadId);
        Lead lead=new Lead();
        lead.setLeadStatus(LeadStatus.valueOf("NEW_LEAD"));
        lead.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return  lead;
    }
}
