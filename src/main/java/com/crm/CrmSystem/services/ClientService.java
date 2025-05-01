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

import java.util.List;
import java.util.Optional;

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

//    public boolean sendCredentials(int id){
//        try {
//
//            SalesLead l1= salesLeadService.findbyId(id).get();
//            String clientEmail = l1.getLead().getLeadsource().getLeadEmail();
//            String clientuserName = l1.getLead().getLeadsource().getLeadName();
//            String password = "123";
//
//            Role r1  = new Role();
//            r1.setRoleId(5);
//            Client client  = new Client();
//            client.setSalesLead(l1);
//            User user = new User(clientEmail,passwordEncoder.encode(password),clientuserName,r1);
//
//            userRepository.save(user);
//            client.setUser(user);
//            clientRepo.save(client);
//            return true;
//        } catch (Exception e) {
//
//            throw new RuntimeException(e);
//        }
//
//    }
public boolean sendCredentials(int id) {
    try {
        SalesLead l1 = salesLeadService.findbyId(id).orElseThrow(() -> new RuntimeException("SalesLead not found"));
        String clientEmail = l1.getLead().getLeadsource().getLeadEmail();
        String clientUserName = l1.getLead().getLeadsource().getLeadName();
        String password = "123";

        // Check if user already exists by email
        Optional<User> existingUser = userRepository.findByEmailId(clientEmail);

        if (existingUser.isPresent()) {
            // User already exists, return or log accordingly
            System.out.println("User already exists: " + clientEmail);
            return false; // or true, depending on your business logic
        }

        // Create new Role
        Role r1 = new Role();
        r1.setRoleId(5);

        // Create new Client and User
        Client client = new Client();
        client.setSalesLead(l1);

        User user = new User(clientEmail, passwordEncoder.encode(password), clientUserName, r1);
        userRepository.save(user);

        client.setUser(user);
        clientRepo.save(client);

        return true;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

    public Optional<Client> getAllService(int id){
    return clientRepo.findById(id);
    }

    public List<Client> getAllClients(){
    return clientRepo.findAll();
    }





}
