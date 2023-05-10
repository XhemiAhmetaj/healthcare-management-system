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

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final UserService userService;
    private final RecommendationRepository recommendationRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentService appointmentService;

    @Override
    public RecommendationDTO addRecommendation(Jwt jwt, RecommendationDTO recommendationDTO) {
        User u = userService.getUserFromToken(jwt);
        Recommendation r = recommendationRepository.save(new Recommendation());
        r.setDescription(recommendationDTO.getDescription());
        r.setRecommendedBy(u);
        r.setAppointment(appointmentRepository.findById(recommendationDTO.getAppointment().getId()).orElseThrow(()-> new ResourceNotFountException(String
                .format("Appointment not found"))));
        r = recommendationRepository.save(r);
        return RecommendationMapper.toDTO(r);
    }
}
