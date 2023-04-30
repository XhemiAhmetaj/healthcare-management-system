package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.entity.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDepartmentRepository extends JpaRepository<EmployeeDepartment,Integer> {
}
