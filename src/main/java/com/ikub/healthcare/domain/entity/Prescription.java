package com.ikub.healthcare.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "prescriptions")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Prescription {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
//    private User writtenBy;
//    private Appointment appointmentId;
}
