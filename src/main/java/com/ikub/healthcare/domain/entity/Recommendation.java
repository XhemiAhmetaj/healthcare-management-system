package com.ikub.healthcare.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recommendations")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Recommendation {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
//    private User recommendedBy;
//    private Appointment appointmentId;
}
