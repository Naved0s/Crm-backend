package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.CrmService;
import com.crm.CrmSystem.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    public CrmService addService(CrmService crmService){
    return  serviceRepository.save(crmService);
    }

    public List<CrmService> getAllService(){
        return serviceRepository.findAll();
    }

    public boolean deleteService(int id){
                serviceRepository.deleteById(id);
        return true;
    }

}
