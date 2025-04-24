package com.crm.CrmSystem.repository;

import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.models.SalesLead;
import com.crm.CrmSystem.models.enums.SalesLeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesLeadRepository extends JpaRepository<SalesLead,Integer> {

    boolean existsByLead(Lead lead);
}
