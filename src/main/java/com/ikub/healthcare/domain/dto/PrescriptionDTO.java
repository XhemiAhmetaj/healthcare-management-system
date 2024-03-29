package com.ikub.healthcare.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionDTO {
    private Integer id;
    private String precription;
    private Integer writtenBy;
    private Integer diagnosisId;


}
