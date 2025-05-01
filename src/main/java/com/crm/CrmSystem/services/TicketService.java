package com.crm.CrmSystem.services;

import com.crm.CrmSystem.models.Ticket;
import com.crm.CrmSystem.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketRepo ticketRepo;

    public void raiseTicket(Ticket t){
        ticketRepo.save(t);
    }
}
