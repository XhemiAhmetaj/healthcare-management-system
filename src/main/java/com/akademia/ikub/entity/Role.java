package com.akademia.ikub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
