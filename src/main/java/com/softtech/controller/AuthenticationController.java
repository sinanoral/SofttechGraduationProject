package com.softtech.controller;

import com.softtech.model.requestDto.LoginDto;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.responseDto.LoginResponseDto;
import com.softtech.service.AuthenticationService;
import com.softtech.utility.results.DataResult;
import com.softtech.utility.results.Result;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public DataResult<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public Result register(@RequestBody UserCreateDto userCreateDto) {
        return authenticationService.register(userCreateDto);
    }
}
