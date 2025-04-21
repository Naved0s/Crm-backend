package com.crm.CrmSystem.models;

import com.crm.CrmSystem.models.enums.SourceType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LeadSource")
public class LeadSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    CrmService crmService;


    @Column(name = "sourceName")
    private String sourceName;


    @Enumerated(EnumType.STRING)
    private SourceType sourceType;


    @Column(name = "description")
    private String description;
}
