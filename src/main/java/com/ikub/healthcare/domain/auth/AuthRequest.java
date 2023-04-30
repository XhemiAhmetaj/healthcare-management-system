package com.ikub.healthcare.domain.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {

    @NotNull @Email
    private String email;
    @NotNull
    private String password;
}
