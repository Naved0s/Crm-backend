package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Leadsource;
import com.crm.CrmSystem.repository.LeadsourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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



}
