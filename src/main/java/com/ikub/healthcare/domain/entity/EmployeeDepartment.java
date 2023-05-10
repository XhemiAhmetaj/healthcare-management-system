package com.ikub.healthcare.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_department")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDepartment {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
}
