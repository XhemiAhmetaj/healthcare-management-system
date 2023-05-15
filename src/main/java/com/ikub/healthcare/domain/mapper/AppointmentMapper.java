package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.User;

import java.time.DayOfWeek;

public class AppointmentMapper {

    public static AppointmentDTO toDto(Appointment app){
        return AppointmentDTO.builder()
                .id(app.getId())
                .description(app.getDescription())
                .scheduledDate(app.getScheduledDate())
//                .scheduleDate(app.getDate())
//                .scheduleTime(app.getTime())
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
//                .date(dto.getScheduleDate())
//                .time(dto.getScheduleTime())
                .scheduledDate(dto.getScheduledDate())
                .build();
    }

    public static Appointment createAppointment(AppointmentDTO dto, User u){
        Appointment a = new Appointment();
        a.setDescription(dto.getDescription());

//            a.setDate(dto.getScheduledDate().toLocalDate());
//            a.setTime(dto.getScheduledDate().toLocalTime());

//        a.setScheduleDateTime(dto.getScheduleDateTimeDTO());
//        a.setScheduledDate(dto.getScheduledDate());
        a.setCreatedBy(u);
        return a;
    }


}
