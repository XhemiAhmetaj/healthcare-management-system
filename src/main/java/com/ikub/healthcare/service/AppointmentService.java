package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.User;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO findAppointmentById(Integer id);
    List<AppointmentDTO> findAllAppointment();
    List<AppointmentDTO> findAppointmentByPatientName(String name);
    AppointmentDTO addAppointment(Jwt jwt, Integer id, AppointmentDTO appointmentDTO);
    List<AppointmentDTO> findAllAppointmentByUserPatientId(Integer id);

    List<AppointmentDTO> findAllAppointmentsByDoctorId(Integer id);

}
