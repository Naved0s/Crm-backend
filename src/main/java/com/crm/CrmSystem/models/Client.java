package com.crm.CrmSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @ManyToOne
    @JoinColumn(name = "salesLeadId")
    private SalesLead salesLead;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


}
