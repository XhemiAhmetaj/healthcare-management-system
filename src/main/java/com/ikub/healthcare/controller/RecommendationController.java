package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.dto.RecommendationDTO;
import com.ikub.healthcare.domain.entity.Recommendation;
import com.ikub.healthcare.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

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
}
