package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.User;

public class AppointmentMapper {

    public static AppointmentDTO toDto(Appointment app){
        return AppointmentDTO.builder()
                .id(app.getId())
                .description(app.getDescription())
                .date(app.getDate())
                .time(app.getTime())
                .doctorDTO(app.getUserDoctor()!=null?UserMapper.toDto(app.getUserDoctor()):null)
                .patientDTO(app.getUserPatient()!=null?UserMapper.toDto(app.getUserPatient()):null)
                .parentAppointment(app.getParent()!=null?AppointmentMapper.toDto(app.getParent()):null)
                .build();
    }

    public static Appointment toEntity(AppointmentDTO dto){
        return Appointment.builder()
                .description(dto.getDescription())
                .userDoctor(dto.getDoctorDTO()!=null?UserMapper.toEntity(dto.getDoctorDTO()):null)
                .userPatient(dto.getPatientDTO()!=null?UserMapper.toEntity(dto.getPatientDTO()):null)
                .date(dto.getDate())
                .time(dto.getTime())
                .build();
    }

    public static Appointment createAppointment(AppointmentDTO dto, User u){
        Appointment a = new Appointment();
        a.setDescription(dto.getDescription());
        a.setCreatedBy(u);
        return a;
    }


}
