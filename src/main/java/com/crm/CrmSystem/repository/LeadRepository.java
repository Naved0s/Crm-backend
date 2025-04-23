package com.crm.CrmSystem.repository;

import com.crm.CrmSystem.models.Lead;
import com.crm.CrmSystem.models.Leadsource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead,Integer> {
    Optional<Lead> findByLeadsource(Leadsource leadsource);
}
