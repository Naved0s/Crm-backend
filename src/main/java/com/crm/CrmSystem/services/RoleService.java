package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Role;
import com.crm.CrmSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role getById(Long id){
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }


}
