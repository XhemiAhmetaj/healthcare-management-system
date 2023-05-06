package com.ikub.healthcare.domain.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ikub.healthcare.domain.entity.enums.Department;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDate birthday;
    @Column(unique = true)
    private String personalNumber;
    @Enumerated(EnumType.STRING)
    private Department department;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_doctor", referencedColumnName = "id")
    private User familyDoctor;
    @ToString.Exclude
    @OneToMany(mappedBy = "familyDoctor", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<User> patients;
    @ToString.Exclude
    @OneToMany(mappedBy = "userPatient")
    private List<Appointment> patient_appointments;
    @ToString.Exclude
    @OneToMany(mappedBy = "userDoctor")
    private List<Appointment> doctor_appointments;
    @CreatedDate
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role.getValue()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
