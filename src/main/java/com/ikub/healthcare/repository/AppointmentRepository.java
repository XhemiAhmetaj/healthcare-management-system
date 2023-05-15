package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.entity.Appointment;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAllByUserPatient_Name(String name);
    List<Appointment> findAllByUserPatient_Id(Integer id);
    List<Appointment> findAllByUserDoctor_Id(Integer id);
    Optional<Appointment> findById(Integer id);
    List<Appointment> findAppointmentsByDate(String date);
//    List<Appointment> findAppointmentsByUserDoctor_IdAndDate_DayOfWeek(Integer id, LocalDate date);

    List<Appointment> findAllByDateAndAndUserDoctor_Id(Integer id, LocalDate date);
}
