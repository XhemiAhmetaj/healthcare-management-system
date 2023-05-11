package com.ikub.healthcare.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnosisDTO {

    private Integer id;
    private String diagnosis;
    private Integer writtenBy;
    private AppointmentDTO appointmentDTO;
}
