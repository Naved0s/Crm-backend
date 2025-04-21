package com.crm.CrmSystem.models;

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
@Table(name = "PointOfContact")
public class PointOfContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pocId;

    private String companyName;
    private String pocPhoneNumber;
    private String pocEmail;
    private String password;
}
