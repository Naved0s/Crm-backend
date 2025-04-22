package com.crm.CrmSystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CrmService {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;

    @Column(name = "serviceName")
    private String serviceName;
    @Column(name = "price")
    private double price;
    @Column(name = "isActive")
    private boolean isActive;
    @Column(name = "durationInDays")
    private int durationInDays;
    @Column(name = "description")
    private String description;





}
