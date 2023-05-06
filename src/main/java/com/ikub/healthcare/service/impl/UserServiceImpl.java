package com.ikub.healthcare.service.impl;

import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.entity.enums.Department;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import com.ikub.healthcare.domain.exception.ResourceNotFountException;
import com.ikub.healthcare.domain.mapper.UserMapper;
import com.ikub.healthcare.repository.UserRepository;
import com.ikub.healthcare.repository.UserRoleRepository;
import com.ikub.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User with username - %s, not found", email)));
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFountException(String.format("User with id %s not found",id)));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(u->UserMapper.toDto(u)).collect(Collectors.toList());
    }

    @Override
    public UserDTO registerUser(UserDTO req, String userRole, String userDepartment) {
        User u = UserMapper.toEntity(req);
        u.setRole(userRole!=null? UserRole.fromValue(userRole):UserRole.PATIENT);
        u.setDepartment(userDepartment!=null?Department.fromValue(userDepartment):Department.NULL);
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        if(u.getRole()==UserRole.PATIENT){
            u.setFamilyDoctor(userRepository.findAllByRoleAndAddressEquals(UserRole.FAMILY_DOCTOR, u.getAddress()));
        }else
            u.setFamilyDoctor(null);
        u = userRepository.save(u);

        return UserMapper.toDto(u);
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public User getUserFromToken(Jwt jwt) {
        String sub = (String) jwt.getClaims().get("sub");
        return userRepository.findByEmail(sub).get();
    }

//    @Override
//    public List<User> findAllDoctors() {
//        return userRepository.findUsersByRole(UserRole.DOCTOR);
//    }
//    @Override
//    public List<User> findAllFamilyDoctors(){
//        return userRepository.findUsersByRole(UserRole.FAMILY_DOCTOR);
//    }

//    @Override
//    public List<UserDTO> findUserByRole(String role) {
//        return userRepository.findUsersByRole(UserRole.fromValue(role)).stream()
//                .map(u->UserMapper.toDto(u))
//                .collect(Collectors.toList());
//    }


}
