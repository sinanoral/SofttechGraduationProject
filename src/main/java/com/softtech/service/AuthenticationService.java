package com.softtech.service;

import com.softtech.enums.EnumJwtConstant;
import com.softtech.model.requestDto.LoginDto;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.responseDto.LoginResponseDto;
import com.softtech.security.JwtTokenGenerator;
import com.softtech.security.JwtUserDetails;
import com.softtech.utility.results.DataResult;
import com.softtech.utility.results.Result;
import com.softtech.utility.results.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public Result register(UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    public DataResult<LoginResponseDto> login(LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenGenerator.generateJwtToken(authentication);
        String bearer = EnumJwtConstant.BEARER.getConstant();

        return new SuccessDataResult<>(new LoginResponseDto(bearer + token), "You are successfully logged in");
    }

//    public User getCurrentUser() {
//
//        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
//
//        User user = null;
//        if (jwtUserDetails != null){
//            user = userService.getByIdWithControl(jwtUserDetails.getId());
//        }
//
//        return user;
//    }

//    public Long getCurrentUserId(){
//
//        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
//
//        return jwtUserDetails.getId();
//    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails) {
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
