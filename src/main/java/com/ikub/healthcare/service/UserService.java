package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.domain.entity.User;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<UserDTO> findAllUsers();
    UserDTO registerUser(UserDTO req, String userRole, String userDepartment);
    void deleteUser(Integer id);
    User getUserFromToken(Jwt jwt);

//    List<User> findAllDoctors();
//    List<User> findAllFamilyDoctors();

//    List<UserDTO> findUserByRole(String role);

}
