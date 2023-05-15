package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import com.ikub.healthcare.domain.exception.BadRequestException;
import com.ikub.healthcare.domain.exception.ResourceNotFoundException;
import com.ikub.healthcare.domain.mapper.AppointmentMapper;
import com.ikub.healthcare.repository.AppointmentRepository;
import com.ikub.healthcare.service.AppointmentService;
import com.ikub.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;

    @Override
    public AppointmentDTO addAppointment(Jwt jwt, Integer id, AppointmentDTO appointmentDTO) {
        User u = userService.getUserFromToken(jwt);
        Appointment a = AppointmentMapper.createAppointment(appointmentDTO,u);

        if(u.getRole()!= UserRole.PATIENT){
            a.setUserDoctor(userService.findById(appointmentDTO.getDoctorDTO().getId()));
            a.setParent(appointmentRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Appointment not found")));
            a.setUserPatient(a.getParent().getUserPatient());
        }else {
            a.setUserPatient(u);
            a.setUserDoctor(u.getFamilyDoctor());
        }

        if(appointmentDTO.getDate().getDayOfWeek().equals(DayOfWeek.SATURDAY) || appointmentDTO.getDate().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new BadRequestException("No work on weekends! Please schedule another day.");
        }
        List<Appointment> list = appointmentRepository
                .findAppointmentsByDateAndUserDoctor_Id(appointmentDTO.getDate(),a.getUserDoctor().getId())
                .stream()
                .filter(app->app.getTime().equals(appointmentDTO.getTime()))
                .collect(Collectors.toList());

        if(list.isEmpty()){
            a.setDate(appointmentDTO.getDate());
            a.setTime(appointmentDTO.getTime());
        }else {
            throw new BadRequestException("Not available! Please schedule another time.");
        }

        a = appointmentRepository.save(a);
        return AppointmentMapper.toDto(a);
    }

    @Override
    public AppointmentDTO findAppointmentById(Integer id) {
        return appointmentRepository.findById(id)
                .map(AppointmentMapper::toDto)
                .orElseThrow(()->new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public List<AppointmentDTO> findAllAppointment() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentMapper::toDto)
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
    public List<AppointmentDTO> findAppointmentsByDate(String date) {
        return appointmentRepository.findByDate(LocalDate.parse(date)).stream().map(AppointmentMapper::toDto).collect(Collectors.toList());    }

    @Override
    public List<AppointmentDTO> findAppointmentsByUserId(Integer id) {
        User u = userService.findById(id);
        if(u.getRole()== UserRole.PATIENT){
            return appointmentRepository.findAllByUserPatient_Id(id)
                    .stream()
                    .map(AppointmentMapper::toDto)
                    .collect(Collectors.toList());
        }else{
            return appointmentRepository.findAllByUserDoctor_Id(id)
                    .stream()
                    .map(AppointmentMapper::toDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<AppointmentDTO> findAppointmentsByDateAndUserDoctorId(String date, Integer id) {
        return appointmentRepository.findAppointmentsByDateAndUserDoctor_Id(LocalDate.parse(date),id)
                .stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }


}
