package com.softtech.service;

import com.softtech.dao.UserDao;
import com.softtech.mapper.UserMapper;
import com.softtech.model.entity.User;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.utility.results.Result;
import com.softtech.utility.results.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Result create(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userDao.save(user);

        return new SuccessResult("Your account has been successfully created");
    }

    public User getByIdWithControl(Long id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public User findByUserName(String username) {
        return userDao.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found!"));
    }
}
