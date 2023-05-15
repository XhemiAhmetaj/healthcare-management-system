package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.dto.RecommendationDTO;
import com.ikub.healthcare.domain.dto.ResultDTO;
import com.ikub.healthcare.domain.entity.Recommendation;
import com.ikub.healthcare.service.RecommendationService;
import com.ikub.healthcare.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final ResultService resultService;

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR" })
    @GetMapping
    public ResponseEntity<List<RecommendationDTO>> findAllRecommendations(){
        return ResponseEntity.ok(recommendationService.findAllRecommendations());
    }
    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR" })
    @GetMapping("/{id}")
    public ResponseEntity<RecommendationDTO> findRecommendationById(@PathVariable Integer id){
        return ResponseEntity.ok(recommendationService.findRecommendationById(id));
    }

    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR" })
    @GetMapping("/patient/name/{name}")
    public ResponseEntity<List<RecommendationDTO>> findPatientRecommendations(@PathVariable String name){
        return ResponseEntity.ok(recommendationService.findRecommendationByPatientName(name));
    }
    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR" })
    @GetMapping("/doctor/id/{id}")
    public ResponseEntity<List<RecommendationDTO>> findDoctorRecommendations(@PathVariable Integer id){
        return ResponseEntity.ok(recommendationService.findRecommendationByDoctorId(id));
    }
    @RolesAllowed({"RECEPTIONIST","DOCTOR", "FAMILY_DOCTOR" })
    @PostMapping("/{id}/add")
    public ResponseEntity<ResultDTO> addResult(@PathVariable Integer id, @RequestBody ResultDTO resultDTO){
        return ResponseEntity.ok(resultService.addResult(resultDTO,id));
    }
}
