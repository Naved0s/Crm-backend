package com.crm.CrmSystem.repository;

import com.crm.CrmSystem.models.Leadsource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadsourceRepository extends JpaRepository<Leadsource,Integer> {
    @Query("SELECT ls FROM Leadsource ls WHERE ls.id NOT IN (SELECT l.leadsource.id FROM Lead l)")
    List<Leadsource> findAllUnusedLeadSources();
}
