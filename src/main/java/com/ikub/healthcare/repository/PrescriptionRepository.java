package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    List<Prescription> findPrescriptionsByWrittenBy_Id(Integer id);
    List<Prescription> findPrescriptionsByDiagnosis_AppointmentDiag_UserPatient_Id(Integer id);
}
