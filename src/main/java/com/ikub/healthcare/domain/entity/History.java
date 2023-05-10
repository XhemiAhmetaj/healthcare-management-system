package com.ikub.healthcare.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class History {
    @Id
    @GeneratedValue()
    private Integer id;
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//    @OneToMany(mappedBy = "history", fetch = FetchType.LAZY)
//    private List<Appointment> appointmentList;
}
