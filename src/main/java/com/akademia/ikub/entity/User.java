package com.akademia.ikub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
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
    private String personalNumber;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private List<Role> roles = new ArrayList<>();
    @CreatedDate
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

}
