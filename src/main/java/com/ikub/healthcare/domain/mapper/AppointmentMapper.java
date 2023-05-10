package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;

public class AppointmentMapper {

    public static AppointmentDTO toDto(Appointment app){
        return AppointmentDTO.builder()
                .id(app.getId())
                .description(app.getDescription())
                .scheduledDate(app.getScheduledDate())
                .doctorDTO(app.getUserDoctor()!=null?UserMapper.toDto(app.getUserDoctor()):null)
                .patientDTO(app.getUserPatient()!=null?UserMapper.toDto(app.getUserPatient()):null)
                .parentAppointment(app.getParent()!=null?AppointmentMapper.toDto(app.getParent()):null)
                .build();
    }

}
