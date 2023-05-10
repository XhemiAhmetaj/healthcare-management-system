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
        System.err.println("Kaloi userin");
        Recommendation r = recommendationRepository.save(new Recommendation());
        r.setDescription(recommendationDTO.getDescription());
        System.err.println("Kaloi get description");

        r.setRecommendedBy(u);
        System.err.println("Kaloi get recommended by");

//        Appointment a = appointmentRepository.findById(recommendationDTO.getAppointment().getId()).orElseThrow(()-> new ResourceNotFountException(String
//                .format("Product with id %s not found",recommendationDTO)));;
        r.setAppointment(appointmentRepository.findById(recommendationDTO.getAppointment().getId()).orElseThrow(()-> new ResourceNotFountException(String
                .format("Product with id %s not found",recommendationDTO))));
        System.err.println("Kaloi get appointment");

        r = recommendationRepository.save(r);
        return RecommendationMapper.toDTO(r);
    }
}
