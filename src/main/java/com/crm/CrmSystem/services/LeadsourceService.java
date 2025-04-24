package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Leadsource;
import com.crm.CrmSystem.repository.LeadsourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadsourceService {

    @Autowired
    LeadsourceRepository leadsourceRepository;

    //adding leadsource logic
    public Leadsource addLeadsource(Leadsource leadsource){
       return leadsourceRepository.save(leadsource);
    }

    //getting all leadsource
    public List<Leadsource> getall(){

        return leadsourceRepository.findAll();
    }

    //delete a lead source
    public boolean removeLeadSource(int id){
        if(leadsourceRepository.findById(id).isPresent()){
            leadsourceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //Edit entities in leadsource
    public void editLeadsource(int id,Leadsource ls){
        Optional<Leadsource> l2 = leadsourceRepository.findById(id);
        if (l2.isPresent()) {
            Leadsource existing = l2.get();

            if (ls.getCompanyAdd() != null)
                existing.setCompanyAdd(ls.getCompanyAdd());

            if (ls.getCrmService() != null)
                existing.setCrmService(ls.getCrmService());

            if (ls.getDescription() != null)
                existing.setDescription(ls.getDescription());

            if (ls.getContactNo() != null)
                existing.setContactNo(ls.getContactNo());

            if (ls.getLeadEmail() != null)
                existing.setLeadEmail(ls.getLeadEmail());

            if (ls.getSourceType() != null)
                existing.setSourceType(ls.getSourceType());

            if (ls.getLeadName() != null)
                existing.setLeadName(ls.getLeadName());
            if(ls.getCompanyName() != null)
                existing.setCompanyName(ls.getCompanyName());

            leadsourceRepository.save(existing);
        }

        else {
            throw new RuntimeException("Leadsource not found with id " + id);
        }
    }


}
