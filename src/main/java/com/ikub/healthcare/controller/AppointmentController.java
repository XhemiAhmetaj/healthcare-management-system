package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.dto.DiagnosisDTO;
import com.ikub.healthcare.domain.dto.RecommendationDTO;
import com.ikub.healthcare.service.AppointmentService;
import com.ikub.healthcare.service.DiagnosisService;
import com.ikub.healthcare.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final RecommendationService recommendationService;
    private final DiagnosisService diagnosisService;

    @RolesAllowed({"PATIENT", "RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @PostMapping("/add")
    public ResponseEntity<AppointmentDTO> createAppointment(@AuthenticationPrincipal Jwt jwt, Integer id, @RequestBody AppointmentDTO app){
        AppointmentDTO appointmentDTO = appointmentService.addAppointment(jwt,null, app);
        return ResponseEntity.ok(appointmentDTO);
    }

    @RolesAllowed({"PATIENT", "RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @PostMapping("{id}/add")
    public ResponseEntity<AppointmentDTO> addAppointment(@AuthenticationPrincipal Jwt jwt,@PathVariable Integer id, @RequestBody AppointmentDTO app){
        AppointmentDTO appointmentDTO = appointmentService.addAppointment(jwt,id, app);
        return ResponseEntity.ok(appointmentDTO);
    }

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping("/patient/id/{id}")
    public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByPatientId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.findAllAppointmentByUserPatientId(id));
    }

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping("/patient/name/{name}")
    public ResponseEntity<List<AppointmentDTO>> findAppointmentsByPatientName(@PathVariable String name){
        return ResponseEntity.ok(appointmentService.findAppointmentByPatientName(name));
    }

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> findAppointmentById(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.findAppointmentById(id));
    }

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping("/doctor/id/{id}")
    public ResponseEntity<List<AppointmentDTO>> findAppointmentByDoctorId(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.findAllAppointmentsByDoctorId(id));
    }


    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping()
    public ResponseEntity<List<AppointmentDTO>> findAllAppointments(){
        return ResponseEntity.ok(appointmentService.findAllAppointment());
    }

    @RolesAllowed({"DOCTOR", "FAMILY_DOCTOR" })
    @PostMapping("{id}/recommendation/add")
    public ResponseEntity<RecommendationDTO> addRecommendation(@AuthenticationPrincipal Jwt jwt,@PathVariable Integer id, @RequestBody RecommendationDTO rec){
        RecommendationDTO recommendation = recommendationService.addRecommendation(jwt,id, rec);
        return ResponseEntity.ok(recommendation);
    }
    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @PostMapping("/{id}/diagnosis/add")
    public ResponseEntity<DiagnosisDTO> addDiagnosis(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer id, @RequestBody DiagnosisDTO diagnosis){
        return ResponseEntity.ok(diagnosisService.addDiagnosis(jwt,id,diagnosis));
    }

}
