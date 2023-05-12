package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.ResultDTO;
import com.ikub.healthcare.domain.entity.Recommendation;
import com.ikub.healthcare.domain.entity.Result;

public class ResultMapper {

    public static ResultDTO toDto(Result result){
        return ResultDTO.builder()
                .id(result.getId())
                .results(result.getResults())
                .recommendationId(result.getRecommendation().getId())
                .build();
    }

    public static Result toEntityResult(ResultDTO dto, Recommendation rec){
        return Result.builder()
                .id(dto.getId())
                .results(dto.getResults())
                .recommendation(rec)
                .build();
    }
}
