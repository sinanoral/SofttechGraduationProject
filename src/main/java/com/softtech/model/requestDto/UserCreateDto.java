package com.softtech.model.requestDto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserCreateDto {

    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    @Length(min = 6, max = 20)
    private String password;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;
}
