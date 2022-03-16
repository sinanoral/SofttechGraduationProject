package com.softtech.service;

import com.softtech.dao.UserDao;
import com.softtech.mapper.UserMapper;
import com.softtech.model.entity.User;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.requestDto.UserUpdateDto;
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
        if(userDao.existsAllByUserName(userCreateDto.getUserName()))
            throw new RuntimeException("User name already exists!");

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

    public Result update(Long id, UserUpdateDto userUpdateDto) {
        User user = getByIdWithControl(id);
//
//        String userNameToUpdate = userUpdateDto.getUserName();
//        if (!(userNameToUpdate == null || userNameToUpdate.isEmpty() || userNameToUpdate.trim().isEmpty())) {
//            if(!userDao.existsAllByUserName(userNameToUpdate))
//                user.setUserName(userUpdateDto.getUserName());
//        }
//
//        String nameToUpdate = userUpdateDto.getName();
//        if (!(nameToUpdate == null || nameToUpdate.isEmpty() || nameToUpdate.trim().isEmpty()))
//            user.setName(userUpdateDto.getName());
//
//        String surnameToUpdate = userUpdateDto.getSurname();
//        if (!(surnameToUpdate == null || surnameToUpdate.isEmpty() || surnameToUpdate.trim().isEmpty()))
//            user.setSurname(userUpdateDto.getSurname());
//
//        String passwordToUpdate = userUpdateDto.getPassword();
//        if (!(passwordToUpdate == null || passwordToUpdate.isEmpty() || passwordToUpdate.trim().isEmpty())) {
//            String password = passwordEncoder.encode(passwordToUpdate);
//            user.setPassword(password);
//        }

        userDao.save(user);
        return new SuccessResult("User updated!");
    }

    public Result delete(Long id) {
        User user = getByIdWithControl(id);
        userDao.delete(user);

        return new SuccessResult("User has been successfully deleted!");
    }
}
