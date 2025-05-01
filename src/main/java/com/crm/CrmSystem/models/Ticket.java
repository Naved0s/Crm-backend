package com.crm.CrmSystem.models;

import com.crm.CrmSystem.models.enums.TicketStatus;
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
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int TicketId;

    private String subject;

    @OneToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private String DateTime;

}
