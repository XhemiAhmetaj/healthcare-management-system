package com.ikub.healthcare.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    @ManyToOne()
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private User userPatient;
    @ManyToOne()
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User userDoctor;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime scheduledDate;
}
