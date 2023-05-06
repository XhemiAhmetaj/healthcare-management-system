package com.ikub.healthcare.repository;

import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
//    List<User> findAllByRole(UserRole role);
    List<User> findUsersByRole(UserRole userRole);
    User findAllByRoleAndAddressEquals(UserRole role, String address);

//    List<User> findUsersByRoleValue(String role);


}
