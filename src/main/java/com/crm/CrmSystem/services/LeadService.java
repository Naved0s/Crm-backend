package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Leadsource;
import com.crm.CrmSystem.models.enums.LeadStatus;
import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.repository.LeadsourceRepository;
import com.crm.CrmSystem.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeadService {
    @Autowired
    LeadRepository leadRepository;

    @Autowired
    LeadsourceRepository leadsourceRepository;

    //add leads from leadsource
    public void addleads(int leadId){
      Optional<Leadsource> l = leadsourceRepository.findById(leadId);
        System.out.println(l.get().getLeadName());
     Lead l1 = new Lead();
     l1.setLeadsource(l.get());
     l1.setLeadStatus(LeadStatus.valueOf("NEW_LEAD"));
     leadRepository.save(l1);
    }

    //change status of lead
    public boolean changeStatus(int id, String newStatus){
        if (leadRepository.existsById(id)){
            Lead l = leadRepository.findById(id).get();
                    l.setLeadStatus(LeadStatus.valueOf(newStatus));
            System.out.println("Lead status changed: "+ id + newStatus);
                    leadRepository.save(l);
            return true;
        }
      return false;
    }
}
