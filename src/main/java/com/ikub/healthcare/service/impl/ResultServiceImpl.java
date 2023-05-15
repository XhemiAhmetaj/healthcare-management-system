package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.ResultDTO;
import com.ikub.healthcare.domain.entity.Result;
import com.ikub.healthcare.domain.exception.ResourceNotFoundException;
import com.ikub.healthcare.domain.mapper.ResultMapper;
import com.ikub.healthcare.repository.RecommendationRepository;
import com.ikub.healthcare.repository.ResultRepository;
import com.ikub.healthcare.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final RecommendationRepository recommendationRepository;

    @Override
    public ResultDTO addResult(ResultDTO resultDTO, Integer id) {
        Result r = ResultMapper.toEntityResult(resultDTO,recommendationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recommendation not found")));
        r = resultRepository.save(r);
        return ResultMapper.toDto(r);
    }

    @Override
    public List<ResultDTO> findResults() {
        return resultRepository.findAll().stream()
                .map(ResultMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultDTO> findResultsByRecommendationId(Integer id) {
        return resultRepository.findResultsByRecommendation_Id(id).stream()
                .map(ResultMapper::toDto)
                .collect(Collectors.toList());
    }
}
