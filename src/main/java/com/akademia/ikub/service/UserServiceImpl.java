package com.akademia.ikub.service;

import com.akademia.ikub.dto.RoleDTO;
import com.akademia.ikub.dto.UserRegistrationDTO;
import com.akademia.ikub.entity.Role;
import com.akademia.ikub.entity.User;
import com.akademia.ikub.mapper.UserMapper;
import com.akademia.ikub.repository.UserRepository;
import com.akademia.ikub.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;

    @Override
    public RoleDTO addRole(String roleName) {
        Role r = roleRepository.save(Role.builder().name(roleName).build());
        return UserMapper.toDto(r);
    }

    @Override
    public UserRegistrationDTO registerUser(Integer roleId, UserRegistrationDTO req) {
        Role r = roleRepository.findById(roleId).orElse(null);
        User u = UserMapper.toEntity(req);
        if(u.getRoles()!=null){
            u.getRoles().add(r);
        }else {
            u.setRoles(Arrays.asList(r));
        }
        u = userRepository.save(u);
        return UserMapper.toDto(u);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }


}
