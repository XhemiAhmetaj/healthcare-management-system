package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.RecommendationDTO;
import org.springframework.security.oauth2.jwt.Jwt;

public interface RecommendationService {
    RecommendationDTO addRecommendation(Jwt jwt, RecommendationDTO recommendationDTO);
}
