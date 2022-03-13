package com.softtech.model.requestDto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotEmpty
    @NotNull
    @Length(min = 3, max = 20, message = "User name should be 3 to 20 character long!")
    private String userName;

    @NotEmpty
    @NotNull
    @Length(min = 6, max = 20, message = "Password should be 6 to 20 character long!")
    private String password;
}
