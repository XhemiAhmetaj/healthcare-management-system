package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.DiagnosisDTO;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface DiagnosisService {

    DiagnosisDTO addDiagnosis(Jwt jwt, Integer appointmentId, DiagnosisDTO diagnosisDTO);
    List<DiagnosisDTO> findAllDiagnosis();
    DiagnosisDTO findDiagnosisById(Integer id);
    List<DiagnosisDTO> findDiagnosisByUserId(Integer id);
    List<DiagnosisDTO> findDiagnosisByPatientName(String name);

}
