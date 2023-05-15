package com.ikub.healthcare.domain.entity.enums;

import com.ikub.healthcare.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;
@AllArgsConstructor
public enum UserRole {

    PATIENT("PATIENT"),
    DOCTOR("DOCTOR"),
    FAMILY_DOCTOR("FAMILY_DOCTOR"),
    RECEPTIONIST("RECEPTIONIST"),
    ADMIN("ADMIN");

    private String value;

    public static UserRole fromValue(String userRole){
        return Arrays.asList(UserRole.values())
                .stream().filter(r -> r.value.equals(userRole))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Role %s not found",userRole)));
    }

    public String getValue() {
        return value;
    }
}
