package com.ikub.healthcare.controller;

import com.ikub.healthcare.domain.dto.ResultDTO;
import com.ikub.healthcare.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/results")
public class ResultsController {
    private final ResultService resultService;

    @GetMapping
    public ResponseEntity<List<ResultDTO>> listResults(){
        return ResponseEntity.ok(resultService.findResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> findResultById(@PathVariable Integer id){
        return ResponseEntity.ok(resultService.findById(id));
    }

    @GetMapping("/recommendation/{id}")
    public ResponseEntity<List<ResultDTO>> findResultsByRecommendation(@PathVariable Integer id){
        return ResponseEntity.ok(resultService.findResultsByRecommendationId(id));
    }

}
