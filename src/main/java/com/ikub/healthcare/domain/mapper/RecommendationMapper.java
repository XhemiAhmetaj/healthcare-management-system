package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.RecommendationDTO;
import com.ikub.healthcare.domain.entity.Appointment;
import com.ikub.healthcare.domain.entity.Recommendation;
import com.ikub.healthcare.domain.entity.User;

public class RecommendationMapper {

    public static RecommendationDTO toDTO(Recommendation rec){
        return RecommendationDTO.builder()
                .id(rec.getId())
                .description(rec.getDescription())
                .appointment(rec.getAppointment()!=null?AppointmentMapper.toDto(rec.getAppointment()):null)
                .recommendedBy(UserMapper.toDto(rec.getRecommendedBy()))
                .build();
    }

    public static Recommendation toEntity(RecommendationDTO dto){
        return Recommendation.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .recommendedBy(UserMapper.toEntity(dto.getRecommendedBy()))
                .appointment(AppointmentMapper.toEntity(dto.getAppointment()))
                .build();
    }

    public static Recommendation buildRecommendation(RecommendationDTO dto, User u, Appointment app){
        Recommendation rec = new Recommendation();
        rec.setDescription(dto.getDescription());
        rec.setRecommendedBy(u);
        rec.setAppointment(app);
        return rec;
    }
}
