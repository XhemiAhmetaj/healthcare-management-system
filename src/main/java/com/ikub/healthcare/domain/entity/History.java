package com.ikub.healthcare.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class History {
    @Id
    @GeneratedValue
    private Integer id;
//    private User user;
//    private List<Appointment> appointmentList;
}
