package com.crm.CrmSystem.repository;

import com.crm.CrmSystem.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Integer> {
}
