package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.entity.enums.Department;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import com.ikub.healthcare.domain.mapper.AppointmentMapper;
import com.ikub.healthcare.repository.AppointmentRepository;
import com.ikub.healthcare.repository.UserRepository;
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
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public AppointmentDTO findAppointmentById(Integer id) {
        return null;
    }

    @Override
    public List<AppointmentDTO> findAllAppointment() {
        return appointmentRepository.findAll().stream().map(a-> AppointmentMapper.toDto(a)).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findAppointmentByPatientName(String name) {
        return appointmentRepository.findAllByUserPatient_Name(name)
                .stream().map(AppointmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO addAppointment(Jwt jwt, AppointmentDTO appointmentDTO) {
        User u = userService.getUserFromToken(jwt);
        Appointment a = appointmentRepository.save(new Appointment());
        a.setDescription(appointmentDTO.getDescription());
        a.setScheduledDate(appointmentDTO.getScheduledDate());
        if(u.getRole()!= UserRole.PATIENT){
            a.setUserDoctor(u);
            a.setUserPatient(u);
        }else {
            a.setUserPatient(u);
            a.setUserDoctor(u.getFamilyDoctor());
        }
        a = appointmentRepository.save(a);
        return AppointmentMapper.toDto(a);
    }

    @Override
    public List<Appointment> findAllAppointmentByUserPatientId(Integer id) {
        return appointmentRepository.findAllByUserPatient_Id(id);
    }

}
