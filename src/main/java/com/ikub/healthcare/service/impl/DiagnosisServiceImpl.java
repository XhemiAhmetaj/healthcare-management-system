package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.DiagnosisDTO;
import com.ikub.healthcare.domain.entity.Diagnosis;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.exception.ResourceNotFountException;
import com.ikub.healthcare.domain.mapper.DiagnosisMapper;
import com.ikub.healthcare.repository.AppointmentRepository;
import com.ikub.healthcare.repository.DiagnosisRepository;
import com.ikub.healthcare.service.DiagnosisService;
import com.ikub.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final UserService userService;
    private final DiagnosisRepository diagnosisRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public DiagnosisDTO addDiagnosis(Jwt jwt, Integer appointmentId, DiagnosisDTO diagnosisDTO) {
        User u = userService.getUserFromToken(jwt);
        Diagnosis d = DiagnosisMapper
                .createDiagnosis(diagnosisDTO,u,appointmentRepository.findById(appointmentId)
                        .orElseThrow(()-> new ResourceNotFountException(String
                                .format("Appointment not found"))));
        d = diagnosisRepository.save(d);
        return DiagnosisMapper.toDto(d);
    }

    @Override
    public List<DiagnosisDTO> findAllDiagnosis() {
        return diagnosisRepository.findAll()
                .stream()
                .map(diagnosis -> DiagnosisMapper.toDto(diagnosis))
                .collect(Collectors.toList());
    }

    @Override
    public DiagnosisDTO findDiagnosisById(Integer id) {
        return diagnosisRepository.findById(id)
                .map(diagnosis -> DiagnosisMapper.toDto(diagnosis))
                .orElseThrow(()-> new ResourceNotFountException(String
                        .format("Diagnosis not found")));
    }

    @Override
    public List<DiagnosisDTO> findDiagnosisByPatientId(Integer id) {
        return diagnosisRepository.findDiagnosesByAppointmentDiag_UserPatient_Id(id)
                .stream()
                .map(diagnosis -> DiagnosisMapper.toDto(diagnosis))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiagnosisDTO> findDiagnosisByPatientName(String name) {
        return diagnosisRepository.findDiagnosesByAppointmentDiag_UserPatient_Name(name)
                .stream()
                .map(diagnosis -> DiagnosisMapper.toDto(diagnosis))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiagnosisDTO> findDiagnosisByDoctorId(Integer id) {
        return diagnosisRepository.findDiagnosesByWrittenBy_Id(id)
                .stream()
                .map(diagnosis -> DiagnosisMapper.toDto(diagnosis))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiagnosisDTO> findDiagnosisByDoctorName(String name) {
        return diagnosisRepository.findDiagnosesByWrittenBy_Name(name)
                .stream()
                .map(diagnosis -> DiagnosisMapper.toDto(diagnosis))
                .collect(Collectors.toList());
    }
}
