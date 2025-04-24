package com.crm.CrmSystem.models;

import com.crm.CrmSystem.models.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Leadsource leadsource;

    @Enumerated(EnumType.STRING)
    private LeadStatus leadStatus;

    private LocalDateTime timeStamp;





}
