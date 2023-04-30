package com.ikub.healthcare.service;

import com.ikub.healthcare.domain.dto.RoleDTO;
import com.ikub.healthcare.domain.dto.UserRegistrationDTO;
import com.ikub.healthcare.domain.entity.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAllUsers();
    UserRegistrationDTO registerUser(UserRegistrationDTO req, String userRole, String userDepartment);
    void deleteUser(Integer id);


}
