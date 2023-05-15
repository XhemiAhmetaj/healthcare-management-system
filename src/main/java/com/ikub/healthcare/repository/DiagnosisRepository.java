package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis,Integer> {

    List<Diagnosis> findDiagnosesByAppointmentDiag_UserPatient_Id(Integer id);
    List<Diagnosis> findDiagnosesByAppointmentDiag_UserPatient_Name(String name);
    List<Diagnosis> findDiagnosesByWrittenBy_Id(Integer id);
}
