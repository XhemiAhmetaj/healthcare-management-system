package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO findAppointmentById(Integer id);
    List<AppointmentDTO> findAllAppointment();
    List<AppointmentDTO> findAppointmentByPatientName(String name);
    AppointmentDTO addAppointment(Jwt jwt, Integer id, AppointmentDTO appointmentDTO);
    List<AppointmentDTO> findAppointmentsByDate(String date);
    List<AppointmentDTO> findAppointmentsByUserId(Integer id);
    List<AppointmentDTO> findAppointmentsByDateAndUserDoctorId(String date, Integer id);

}
