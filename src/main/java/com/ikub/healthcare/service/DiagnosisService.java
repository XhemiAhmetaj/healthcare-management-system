package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.DiagnosisDTO;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface DiagnosisService {

    DiagnosisDTO addDiagnosis(Jwt jwt, Integer appointmentId, DiagnosisDTO diagnosisDTO);
    List<DiagnosisDTO> findAllDiagnosis();
    DiagnosisDTO findDiagnosisById(Integer id);
    List<DiagnosisDTO> findDiagnosisByPatientId(Integer id);
    List<DiagnosisDTO> findDiagnosisByPatientName(String name);
    List<DiagnosisDTO> findDiagnosisByDoctorId(Integer id);
    List<DiagnosisDTO> findDiagnosisByDoctorName(String name);

}
