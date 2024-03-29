package com.ikub.healthcare.domain.mapper;

import com.ikub.healthcare.domain.dto.UserDTO;
import com.ikub.healthcare.domain.entity.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static UserDTO toDto(User u){
        return UserDTO.builder()
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

    public static User toEntity(UserDTO u){
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
