package com.crm.CrmSystem.models;

import com.crm.CrmSystem.models.enums.SalesLeadStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="SalesLead")
public class SalesLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SalesLeadId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leadId")
    Lead lead;

  // private SalesLeadStatus leadStatus;


    private LocalDateTime ProposedDate;

    private double proposedValue;

    private double closedValue;

    private LocalDateTime ClosedDate;


    @JsonProperty("lead")
    public void SetSalesLeadfromLead(int lid) {
        this.lead = new Lead();
        this.lead.setLeadId(lid);
    }

    @JsonProperty("lead")
    public void setLeadFromJson(Lead leadsource) {
       this.lead = new Lead();
       this.lead.setLeadStatus(leadsource.getLeadStatus());
    }
}
