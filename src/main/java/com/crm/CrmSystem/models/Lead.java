package com.crm.CrmSystem.models;

import com.crm.CrmSystem.models.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`lead`")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pocId")
    private PointOfContact poc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private  User user;

    @Enumerated(EnumType.STRING)
    private LeadStatus leadStatus;



    private Timestamp createdAt;
}
