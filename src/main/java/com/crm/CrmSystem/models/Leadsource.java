package com.crm.CrmSystem.models;

import com.crm.CrmSystem.models.enums.SourceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LeadsourceNew")
public class Leadsource {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int LeadSourceId;

   @Enumerated(EnumType.STRING)
   private SourceType sourceType;

   private String description;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "service_id")
   private CrmService crmService;


   private String LeadName;
   private String ContactNo;
   private String CompanyName;
   private String CompanyAdd;
   private String LeadEmail;




}
