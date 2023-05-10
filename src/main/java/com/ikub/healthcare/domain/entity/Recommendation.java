package com.ikub.healthcare.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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
    @ToString.Exclude
//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommended_by", referencedColumnName = "id")
    private User recommendedBy;
//    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;
}
