package com.akademia.ikub.service;

import com.akademia.ikub.dto.RoleDTO;
import com.akademia.ikub.dto.UserRegistrationDTO;
import com.akademia.ikub.entity.User;

import java.util.List;

public interface UserService {

    RoleDTO addRole(String roleName);
    UserRegistrationDTO registerUser(Integer roleId, UserRegistrationDTO req);

    List<User> findAllUsers();
    void deleteUser(Integer id);


}
