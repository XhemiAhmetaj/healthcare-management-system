package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.RecommendationDTO;
import com.ikub.healthcare.domain.entity.Recommendation;

public class RecommendationMapper {

    public static RecommendationDTO toDTO(Recommendation rec){
        return RecommendationDTO.builder()
                .id(rec.getId())
                .description(rec.getDescription())
                .appointment(rec.getAppointment()!=null?AppointmentMapper.toDto(rec.getAppointment()):null)
                .recommendedBy(UserMapper.toDto(rec.getRecommendedBy()))
                .build();
    }
}
