package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.DiagnosisDTO;
import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.domain.entity.Diagnosis;
import com.ikub.healthcare.service.DiagnosisService;

public class DiagnosisMapper {

    public static DiagnosisDTO toDto(Diagnosis d){
        return DiagnosisDTO.builder()
                .id(d.getId())
                .diagnosis(d.getDiagnosis())
                .writtenBy(d.getAppointmentDiag().getUserDoctor().getId())

                .appointmentDTO(AppointmentMapper.toDto(d.getAppointmentDiag()))
//                .appointmentId(d.getAppointmentDiag().getId())
                .build();
    }

//    public static Diagnosis toEntity(DiagnosisDTO dto){
//        return Diagnosis.builder()
//                .diagnosis(dto.getDiagnosis())
//                .writtenBy()
//                .appointmentDiag(AppointmentMapper.toEntity(dto.getAppointmentDTO()))
//                .build();
//    }
}
