package com.crm.CrmSystem.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email_id")
    private String emailId;

    @ManyToOne
    @JoinColumn(name = "roleId")
    Role role;

    public User( String emailId, String password, String userName ,Role role) {
    this.role = role;
        this.emailId = emailId;
        this.password = password;
        this.userName = userName;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Role_"+role.getRoleName()));
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
