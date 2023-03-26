package com.akademia.ikub.repository;

import com.akademia.ikub.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Role,Integer> {
}
