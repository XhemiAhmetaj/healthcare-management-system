package com.ikub.healthcare.domain.entity.enums;

import com.ikub.healthcare.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Department {
    MEDICINE("MEDICINE"), SURGERY("SURGERY"), GYNAECOLOGY("GYNAECOLOGY"),
    OBSTETRICS("OBSTETRICS"),
    ORL("ORL"), CARDIOLOGY("CARDIOLOGY"),
    STAFF("STAFF"),
    NULL("");
    private String value;

    public static Department fromValue(String department){
        return Arrays.asList(Department.values())
                .stream().filter(d -> d.value.equals(department))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Role %s not found",department)));
    }

    public String getValue() {
        return value;
    }

}
