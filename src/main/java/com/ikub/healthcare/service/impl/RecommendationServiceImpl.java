package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.RecommendationDTO;
import com.ikub.healthcare.domain.entity.Recommendation;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.exception.ResourceNotFoundException;
import com.ikub.healthcare.domain.mapper.RecommendationMapper;
import com.ikub.healthcare.repository.AppointmentRepository;
import com.ikub.healthcare.repository.RecommendationRepository;
import com.ikub.healthcare.service.RecommendationService;
import com.ikub.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final UserService userService;
    private final RecommendationRepository recommendationRepository;
    private final AppointmentRepository appointmentRepository;
    @Override
    public RecommendationDTO addRecommendation(Jwt jwt, Integer id, RecommendationDTO recommendationDTO) {
        User u = userService.getUserFromToken(jwt);
        Recommendation rec = RecommendationMapper
                .buildRecommendation(recommendationDTO, u, appointmentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment not found")));
        rec = recommendationRepository.save(rec);
        return RecommendationMapper.toDTO(rec);
    }

    @Override
    public List<RecommendationDTO> findAllRecommendations() {
        return recommendationRepository.findAll()
                .stream()
                .map(RecommendationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecommendationDTO findRecommendationById(Integer id) {
        return recommendationRepository.findById(id)
                .map(RecommendationMapper::toDTO)
                .orElseThrow(()->new ResourceNotFoundException("Recommendation not found!"));
    }

    @Override
    public List<RecommendationDTO> findRecommendationByPatientName(String name) {
        return recommendationRepository.findRecommendationsByAppointment_UserPatientName(name)
                .stream()
                .map(RecommendationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecommendationDTO> findRecommendationByDoctorId(Integer id) {
        return recommendationRepository.findRecommendationsByRecommendedBy_Id(id)
                .stream()
                .map(RecommendationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
