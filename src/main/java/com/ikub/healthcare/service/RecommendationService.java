package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.RecommendationDTO;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface RecommendationService {
    RecommendationDTO addRecommendation(Jwt jwt, Integer id, RecommendationDTO recommendationDTO);
    List<RecommendationDTO> findAllRecommendations();
    RecommendationDTO findRecommendationById(Integer id);
    List<RecommendationDTO> findRecommendationByPatientName(String name);
    List<RecommendationDTO> findRecommendationByDoctorId(Integer id);


}
