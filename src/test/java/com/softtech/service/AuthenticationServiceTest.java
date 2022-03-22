package com.softtech.service;

import com.softtech.model.requestDto.LoginRequestDto;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.responseDto.UserGetDto;
import com.softtech.security.jwt.JwtUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserService userService;

    @Test
    void whenRegisterCalled_itShouldReturnUserDto() {
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setUserName("test");

        UserCreateDto userCreateDto = new UserCreateDto();
        userCreateDto.setUserName("test");

        when(userService.createUser(any())).thenReturn(userGetDto);

        UserGetDto result = authenticationService.register(userCreateDto);

        assertEquals("test", result.getUserName());
    }
}