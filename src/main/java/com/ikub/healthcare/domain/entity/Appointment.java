package com.ikub.healthcare.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Appointment {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private User userPatient;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User userDoctor;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private Appointment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Appointment> subAppointments;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;
    @ToString.Exclude
    @OneToOne()
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User createdBy;

    @ToString.Exclude
    @OneToOne(mappedBy = "appointment")
    private Recommendation recommendation;

    @ToString.Exclude
    @OneToOne(mappedBy = "appointmentDiag")
    private Diagnosis diagnosis;

}
