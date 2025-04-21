package com.crm.CrmSystem.repository;

import com.crm.CrmSystem.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead,Integer> {
}
