package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAllByUserPatient_Name(String name);
    List<Appointment> findAllByUserPatient_Id(Integer id);
    List<Appointment> findAllByUserDoctor_Id(Integer id);

    Optional<Appointment> findById(Integer id);
}
