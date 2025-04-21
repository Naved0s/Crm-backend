package com.crm.CrmSystem.repository;

import com.crm.CrmSystem.models.CrmService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<CrmService,Integer> {
}
