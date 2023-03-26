package com.akademia.ikub.mapper;

import com.akademia.ikub.dto.RoleDTO;
import com.akademia.ikub.dto.UserRegistrationDTO;
import com.akademia.ikub.entity.Role;
import com.akademia.ikub.entity.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static RoleDTO toDto(Role r){
        return RoleDTO.builder()
                .id(r.getId())
                .name(r.getName())
                .build();
    }

    public static UserRegistrationDTO toDto(User u){
        return UserRegistrationDTO.builder()
                .id(u.getId())
                .name(u.getName())
                .lastname(u.getLastname())
                .personalNumber(u.getPersonalNumber())
                .birthday(u.getBirthday())
                .address(u.getAddress())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .password(u.getPassword())
                .build();
    }

    public static User toEntity(UserRegistrationDTO u){
        return User.builder()
                .name(u.getName())
                .lastname(u.getLastname())
                .personalNumber(u.getPersonalNumber())
                .birthday(u.getBirthday())
                .address(u.getAddress())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .password(u.getPassword())
                .created_at(LocalDateTime.now())
                .modified_at(LocalDateTime.now())
                .build();
    }
}
