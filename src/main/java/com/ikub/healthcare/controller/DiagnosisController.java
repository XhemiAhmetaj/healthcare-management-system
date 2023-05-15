package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.DiagnosisDTO;
import com.ikub.healthcare.domain.dto.PrescriptionDTO;
import com.ikub.healthcare.repository.DiagnosisRepository;
import com.ikub.healthcare.service.DiagnosisService;
import com.ikub.healthcare.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;
    private final PrescriptionService prescriptionService;


    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping("/list")
    public ResponseEntity<List<DiagnosisDTO>> listDiagnosis(){
        return ResponseEntity.ok(diagnosisService.findAllDiagnosis());
    }

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping("/{id}")
    public ResponseEntity<DiagnosisDTO> findDiagnosisById(@PathVariable Integer id){
        return ResponseEntity.ok(diagnosisService.findDiagnosisById(id));
    }

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping("patient/name={name}")
    public ResponseEntity<List<DiagnosisDTO>> findDiagnosisByPatientName(@PathVariable String name){
        return ResponseEntity.ok(diagnosisService.findDiagnosisByPatientName(name));
    }

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR"})
    @GetMapping("user/id={id}")
    public ResponseEntity<List<DiagnosisDTO>> findDiagnosisByPatientId(@PathVariable Integer id){
        return ResponseEntity.ok(diagnosisService.findDiagnosisByUserId(id));
    }

    @RolesAllowed({"DOCTOR", "FAMILY_DOCTOR"})
    @PostMapping("/{id}/precription/add")
    public ResponseEntity<PrescriptionDTO> addPrescription(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer id, @RequestBody PrescriptionDTO prescriptionDTO){
        return ResponseEntity.ok(prescriptionService.addPrescription(jwt, id, prescriptionDTO));
    }
}