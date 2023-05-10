package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.dto.RecommendationDTO;
import com.ikub.healthcare.domain.entity.Recommendation;
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
@RequestMapping("/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @RolesAllowed({"DOCTOR", "FAMILY_DOCTOR" })
    @PostMapping("/add")
    public ResponseEntity<RecommendationDTO> addRecommendation(@AuthenticationPrincipal Jwt jwt, @RequestBody RecommendationDTO rec){
        RecommendationDTO recommendation = recommendationService.addRecommendation(jwt, rec);
        return ResponseEntity.ok(recommendation);
    }
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


}
