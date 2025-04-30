package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Client;
import com.crm.CrmSystem.models.Role;
import com.crm.CrmSystem.models.SalesLead;
import com.crm.CrmSystem.models.User;
import com.crm.CrmSystem.repository.ClientRepo;
import com.crm.CrmSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ClientRepo clientRepo;

    @Autowired
    SalesLeadService salesLeadService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public boolean sendCredentials(int id){
        try {

            SalesLead l1= salesLeadService.findbyId(id).get();
            String clientEmail = l1.getLead().getLeadsource().getLeadEmail();
            String clientuserName = l1.getLead().getLeadsource().getLeadName();
            String password = "123";

            Role r1  = new Role();
            r1.setRoleId(5);
            Client client  = new Client();
            client.setSalesLead(l1);
            User user = new User(clientEmail,passwordEncoder.encode(password),clientuserName,r1);

            userRepository.save(user);
            client.setUser(user);
            clientRepo.save(client);
            return true;
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }
}
