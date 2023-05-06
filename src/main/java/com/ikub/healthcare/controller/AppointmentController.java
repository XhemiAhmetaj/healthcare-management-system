package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.repository.AppointmentRepository;
import com.ikub.healthcare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    @RolesAllowed({"PATIENT", "RECEPTIONIST"})
    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> createAppointment(@AuthenticationPrincipal Jwt jwt, @RequestBody AppointmentDTO app){
        AppointmentDTO appointmentDTO = appointmentService.addAppointment(jwt, app);
        return ResponseEntity.ok(appointmentDTO);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/patient/id/{id}")
    public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByPatientId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.findAllAppointmentByUserPatientId(id));
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/patient/name/{name}")
    public ResponseEntity<List<AppointmentDTO>> findAppointmentsByPatientName(@PathVariable String name){
        return ResponseEntity.ok(appointmentService.findAppointmentByPatientName(name));
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findAppointmentById(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }


    @RolesAllowed("ADMIN")
    @GetMapping()
    public ResponseEntity<List<AppointmentDTO>> findAllAppointments(){
        return ResponseEntity.ok(appointmentService.findAllAppointment());
    }

}
