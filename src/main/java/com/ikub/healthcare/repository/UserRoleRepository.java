package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Role,Integer> {
}
