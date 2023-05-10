package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.User;

public class AppointmentMapper {

    public static AppointmentDTO toDto(Appointment app){
        return AppointmentDTO.builder()
                .id(app.getId())
                .description(app.getDescription())
                .scheduledDate(app.getScheduledDate())
                .doctorDTO(app.getUserDoctor()!=null?UserMapper.toDto(app.getUserDoctor()):null)
                .patientDTO(app.getUserPatient()!=null?UserMapper.toDto(app.getUserPatient()):null)

//                .doctorId(app.getUserDoctor().getId()!=null?app.getUserDoctor().getId():null)
//                .patientId(app.getUserPatient().getId()!=null?app.getUserPatient().getId():null)
                .build();
    }

}
