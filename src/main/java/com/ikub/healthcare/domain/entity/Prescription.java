package com.ikub.healthcare.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
    private String precription;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "written_by", referencedColumnName = "id")
    private User writtenBy;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "diagnosis_id", referencedColumnName = "id")
    private Diagnosis diagnosis;

}
