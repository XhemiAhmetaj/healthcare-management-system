package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.User;
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
        return appointmentRepository.findAll().stream().map(AppointmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> findAppointmentByPatientName(User user) {
        return appointmentRepository.findAllByPatient_id(user.getId())
                .stream().map(AppointmentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO addAppointment(Jwt jwt, AppointmentDTO appointmentDTO) {
        User u = userService.getUserFromToken(jwt);
        Appointment a = appointmentRepository.save(new Appointment());
        return null;
    }

}
