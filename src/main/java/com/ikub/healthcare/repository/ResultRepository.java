package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Integer> {

    List<Result> findResultsByRecommendation_Id(Integer id);
}
