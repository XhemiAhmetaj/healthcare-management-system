package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.PrescriptionDTO;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface PrescriptionService {

    PrescriptionDTO addPrescription(Jwt jwt, Integer id, PrescriptionDTO prescriptionDTO);
    List<PrescriptionDTO> findAllPrecriptions();
    PrescriptionDTO findPrecriptionById(Integer id);
    List<PrescriptionDTO> findUserPrescription(Integer id);
}
