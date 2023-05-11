package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.AppointmentDTO;
import com.ikub.healthcare.domain.dto.RecommendationDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.Recommendation;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.exception.ResourceNotFountException;
import com.ikub.healthcare.domain.mapper.AppointmentMapper;
import com.ikub.healthcare.domain.mapper.RecommendationMapper;
import com.ikub.healthcare.repository.AppointmentRepository;
import com.ikub.healthcare.repository.RecommendationRepository;
import com.ikub.healthcare.service.AppointmentService;
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
        Recommendation r = recommendationRepository.save(new Recommendation());
        r.setDescription(recommendationDTO.getDescription());
        r.setRecommendedBy(u);
        r.setAppointment(appointmentRepository.findById(id).orElseThrow(()-> new ResourceNotFountException(String
                .format("Appointment not found"))));
        r = recommendationRepository.save(r);
        return RecommendationMapper.toDTO(r);
    }

    @Override
    public List<RecommendationDTO> findAllRecommendations() {
        return recommendationRepository.findAll().stream().map(r-> RecommendationMapper.toDTO(r)).collect(Collectors.toList());
    }

    @Override
    public RecommendationDTO findRecommendationById(Integer id) {
        return recommendationRepository.findById(id).map(r->RecommendationMapper.toDTO(r)).orElseThrow(()->new ResourceNotFountException("Recommendation not found!"));
    }

    @Override
    public List<RecommendationDTO> findRecommendationByPatientName(String name) {
        return recommendationRepository.findRecommendationsByAppointment_UserPatientName(name).stream().map(r->RecommendationMapper.toDTO(r)).collect(Collectors.toList());
    }

    @Override
    public List<RecommendationDTO> findRecommendationByDoctorId(Integer id) {
        return recommendationRepository.findRecommendationsByRecommendedBy_Id(id).stream().map(r->RecommendationMapper.toDTO(r)).collect(Collectors.toList());
    }
}
