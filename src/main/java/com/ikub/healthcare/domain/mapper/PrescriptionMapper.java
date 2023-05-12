package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.PrescriptionDTO;
import com.ikub.healthcare.domain.entity.Diagnosis;
import com.ikub.healthcare.domain.entity.Prescription;
import com.ikub.healthcare.domain.entity.User;

public class PrescriptionMapper {

    public static PrescriptionDTO toDto(Prescription p){
        return PrescriptionDTO.builder()
                .id(p.getId())
                .precription(p.getPrecription())
                .writtenBy(p.getDiagnosis().getWrittenBy().getId())
                .diagnosisId(p.getDiagnosis().getId())
                .build();
    }

    public static Prescription toEntityPrescription(PrescriptionDTO dto, User u, Diagnosis d){
        return Prescription.builder()
                .id(dto.getId())
                .precription(dto.getPrecription())
                .writtenBy(u)
                .diagnosis(d)
                .build();
    }
}
