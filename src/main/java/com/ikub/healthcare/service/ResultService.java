package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.ResultDTO;
import com.ikub.healthcare.domain.entity.Recommendation;

import java.util.List;

public interface ResultService {

    ResultDTO addResult(ResultDTO resultDTO, Integer id);
    List<ResultDTO> findResults();
    List<ResultDTO> findResultsByRecommendationId(Integer id);

}
