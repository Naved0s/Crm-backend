package com.crm.CrmSystem.models;

import com.crm.CrmSystem.models.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name="leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadId;

    @ManyToOne
    @JoinColumn(name = "leadSourceId")
    private Leadsource leadsource;

    @Enumerated(EnumType.STRING)
    private LeadStatus leadStatus;




}
