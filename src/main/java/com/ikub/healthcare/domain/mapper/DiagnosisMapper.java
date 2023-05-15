package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.DiagnosisDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.Diagnosis;
import com.ikub.healthcare.domain.entity.User;

public class DiagnosisMapper {

    public static DiagnosisDTO toDto(Diagnosis d){
        return DiagnosisDTO.builder()
                .id(d.getId())
                .diagnosis(d.getDiagnosis())
                .writtenBy(d.getAppointmentDiag().getUserDoctor().getId())
                .appointmentDTO(AppointmentMapper.toDto(d.getAppointmentDiag()))
                .build();
    }

    public static Diagnosis toEntity(DiagnosisDTO dto){
        return Diagnosis.builder()
                .diagnosis(dto.getDiagnosis())
                .writtenBy(UserMapper.toEntity(dto.getAppointmentDTO().getPatientDTO()))
                .appointmentDiag(AppointmentMapper.toEntity(dto.getAppointmentDTO()))
                .build();
    }

    public static Diagnosis createDiagnosis(DiagnosisDTO dto, User u, Appointment appointment){
        Diagnosis d = new Diagnosis();
        d.setDiagnosis(dto.getDiagnosis());
        d.setWrittenBy(u);
        d.setAppointmentDiag(appointment);
        return d;
    }
}
