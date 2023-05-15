package com.ikub.healthcare.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommended_by", referencedColumnName = "id")
    private User recommendedBy;
    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    @OneToMany(mappedBy = "recommendation", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Result> resultList;
}
