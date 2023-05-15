package com.ikub.healthcare.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.management.relation.Role;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ikub.healthcare.domain.entity.User;
import com.ikub.healthcare.domain.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Lastname is required")
    private String lastname;
    private String personalNumber;
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid format")
    private String email;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "Address is required")
    private String address;
    @NotNull(message = "Phone Number is required")
    private String phoneNumber;
    @NotNull(message = "Birth date is required")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

}
