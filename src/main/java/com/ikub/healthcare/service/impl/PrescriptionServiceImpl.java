package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.PrescriptionDTO;
import com.ikub.healthcare.domain.entity.Prescription;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import com.ikub.healthcare.domain.exception.ResourceNotFountException;
import com.ikub.healthcare.domain.mapper.PrescriptionMapper;
import com.ikub.healthcare.repository.DiagnosisRepository;
import com.ikub.healthcare.repository.PrescriptionRepository;
import com.ikub.healthcare.service.PrescriptionService;
import com.ikub.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final UserService userService;
    private final PrescriptionRepository prescriptionRepository;
    private final DiagnosisRepository diagnosisRepository;

    @Override
    public PrescriptionDTO addPrescription(Jwt jwt, Integer id, PrescriptionDTO prescriptionDTO) {
        User u = userService.getUserFromToken(jwt);
        Prescription p = PrescriptionMapper
                .toEntityPrescription(prescriptionDTO,u, diagnosisRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFountException(String
                                .format("Diagnosis not found"))));
        p = prescriptionRepository.save(p);
        return PrescriptionMapper.toDto(p);
    }

    @Override
    public List<PrescriptionDTO> findAllPrecriptions() {
        return prescriptionRepository.findAll()
                .stream()
                .map(prescription -> PrescriptionMapper.toDto(prescription))
                .collect(Collectors.toList());
    }

    @Override
    public PrescriptionDTO findPrecriptionById(Integer id) {
        return prescriptionRepository.findById(id)
                .map(prescription -> PrescriptionMapper.toDto(prescription))
                .orElseThrow(()-> new ResourceNotFountException(String
                    .format("Prescription not found")));
    }

    @Override
    public List<PrescriptionDTO> findUserPrescription(Integer id) {
        User u = userService.findById(id);
        if(u.getRole()== UserRole.PATIENT){
            return prescriptionRepository.findPrescriptionsByDiagnosis_AppointmentDiag_UserPatient_Id(id)
                    .stream()
                    .map(prescription -> PrescriptionMapper.toDto(prescription))
                    .collect(Collectors.toList());
        }else {
            return prescriptionRepository.findPrescriptionsByWrittenBy_Id(id)
                    .stream()
                    .map(prescription -> PrescriptionMapper.toDto(prescription))
                    .collect(Collectors.toList());
        }
    }
}
