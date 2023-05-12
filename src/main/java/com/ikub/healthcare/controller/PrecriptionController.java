package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.PrescriptionDTO;
import com.ikub.healthcare.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prescription")
public class PrecriptionController {

    private final PrescriptionService prescriptionService;

    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> listPrescriptions(){
        return ResponseEntity.ok(prescriptionService.findAllPrecriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PrescriptionDTO>> findUserPrescriptions(@PathVariable Integer id){
        return ResponseEntity.ok(prescriptionService.findUserPrescription(id));
    }
}
