package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(@AuthenticationPrincipal Jwt jwt, @RequestBody AppointmentDTO app){
        AppointmentDTO appointmentDTO = appointmentService.addAppointment(jwt, app);
        return ResponseEntity.ok(appointmentDTO);

    }
}
