package com.crm.CrmSystem.controller;

import com.crm.CrmSystem.models.Client;
import com.crm.CrmSystem.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/myService/{id}")
    public Optional<Client> getClientServiceById(@PathVariable int id){
        return clientService.getAllService(id);
    }


    /*
    {
    "clientId": 1,
    "salesLead": {
        "lead": {
            "leadId": 1,
            "leadsource": {
                "sourceType": "ONLINE",
                "crmService": {
                    "serviceId": 1,
                    "serviceName": "CRM Software",
                    "price": 4999.99,
                    "durationInDays": 100,
                    "description": "Complete CRM solution for managing leads and clients",
                    "active": true
                },
                "timeStamp": "2025-04-30T23:03",
                "active": true,
                "leadEmail": "12naved@gmail.com",
                "leadName": "Naved",
                "companyAdd": "Pune",
                "contactNo": "1234567890",
                "companyName": "NavedsEnterprice",
                "leadSourceId": 1
            },
            "leadStatus": "QUALIFIED",
            "timeStamp": "2025-04-30T23:03:19.627142400"
        },
        "proposedValue": 100000.0,
        "closedValue": 99999.0,
        "dealName": "Final deal",
        "dealStatus": "WON",
        "proposedDate": null,
        "closedDate": "2025-04-30T23:05",
        "salesLeadId": 1
    },
    "user": {
        "userId": 9,
        "password": "$2a$10$EYJLZ5NVY35T969b.YlnmugbsgTfUEyF9xCauYQXIdPgVXNhaWufK",
        "emailId": "12naved@gmail.com",
        "role": {
            "roleId": 5,
            "roleName": "client"
        },
        "username": "Naved",
        "authorities": [
            {
                "authority": "Role_5"
            }
        ],
        "enabled": true,
        "credentialsNonExpired": true,
        "accountNonExpired": true,
        "accountNonLocked": true
    }
}
     */

    /*

     */


    //get all clients
    @GetMapping("/getAll")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

}
