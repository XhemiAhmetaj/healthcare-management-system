package com.ikub.healthcare.domain.dto;

import com.ikub.healthcare.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendationDTO {
    private Integer id;
    private String description;
    private AppointmentDTO appointment;
    private UserDTO recommendedBy;
}
