package com.crm.CrmSystem.models;

import com.crm.CrmSystem.models.enums.SalesLeadStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private SalesLeadStatus leadStatus;

    @JsonProperty("lead")
    public void SetSalesLeadfromLead(int lid) {
        this.lead = new Lead();
        this.lead.setLeadId(lid);
    }
}
