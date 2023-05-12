package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import com.ikub.healthcare.domain.exception.ResourceNotFountException;
import com.ikub.healthcare.domain.mapper.AppointmentMapper;
import com.ikub.healthcare.repository.AppointmentRepository;
import com.ikub.healthcare.repository.DiagnosisRepository;
import com.ikub.healthcare.service.AppointmentService;
import com.ikub.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DiagnosisServiceImpl diagnosisService;
    private final DiagnosisRepository diagnosisRepository;
    private final UserService userService;

    @Override
    public AppointmentDTO addAppointment(Jwt jwt, Integer id, AppointmentDTO appointmentDTO) {
        User u = userService.getUserFromToken(jwt);
        Appointment a = AppointmentMapper.createAppointment(appointmentDTO,u);
        if(u.getRole()!= UserRole.PATIENT){
            a.setUserDoctor(userService.findById(appointmentDTO.getDoctorDTO().getId()));
            a.setUserPatient(userService.findById(appointmentDTO.getPatientDTO().getId()));
            a.setParent(appointmentRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFountException("Appointment not found")));
        }else {
            a.setUserPatient(u);
            a.setUserDoctor(u.getFamilyDoctor());
        }
        a = appointmentRepository.save(a);
        return AppointmentMapper.toDto(a);
    }

    @Override
    public AppointmentDTO findAppointmentById(Integer id) {
        return appointmentRepository.findById(id)
                .map(appointment -> AppointmentMapper.toDto(appointment))
                .orElseThrow(()->new ResourceNotFountException("Appointment not found"));
    }

    @Override
    public List<AppointmentDTO> findAllAppointment() {
        return appointmentRepository.findAll()
                .stream()
                .map(a-> AppointmentMapper.toDto(a))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findAppointmentByPatientName(String name) {
        return appointmentRepository.findAllByUserPatient_Name(name)
                .stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findAllAppointmentByUserPatientId(Integer id) {
        return appointmentRepository.findAllByUserPatient_Id(id)
                .stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findAllAppointmentsByDoctorId(Integer id) {
        return appointmentRepository.findAllByUserDoctor_Id(id)
                .stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }


}
