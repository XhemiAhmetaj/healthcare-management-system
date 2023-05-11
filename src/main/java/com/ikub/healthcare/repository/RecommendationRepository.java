package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation,Integer> {
    List<Recommendation> findRecommendationsByAppointment_UserPatientName(String name);
    List<Recommendation> findRecommendationsByRecommendedBy_Id(Integer id);

}
