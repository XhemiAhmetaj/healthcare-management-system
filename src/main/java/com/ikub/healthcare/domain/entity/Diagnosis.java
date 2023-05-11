package com.ikub.healthcare.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "diagnosis")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Diagnosis {
    @Id
    @GeneratedValue
    private Integer id;
    private String diagnosis;

    @OneToOne
    @JoinColumn(name = "writen_by", referencedColumnName = "id")
    private User writtenBy;

    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointmentDiag;

}
