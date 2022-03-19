package com.softtech.service;

import com.softtech.dao.UserDao;
import com.softtech.enums.UserErrorMessage;
import com.softtech.exceptions.DuplicateEntityException;
import com.softtech.exceptions.EntityNotFoundException;
import com.softtech.mapper.UserMapper;
import com.softtech.model.entity.User;
import com.softtech.model.requestDto.UserCreateDto;
import com.softtech.model.requestDto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final UserMapper mapper;

    public void createUser(UserCreateDto userCreateDto) {
        checkUserNameAvailability(userCreateDto.getUserName());
        User user = mapper.userCreateDtoToUser(userCreateDto);
        userDao.save(user);
    }

    public User getUserByIdWithControl(Long id) {
        return userDao.findById(id).orElseThrow(() ->
                new EntityNotFoundException(UserErrorMessage.USER_NOT_FOUND_ID));
    }

    public User findUserByUserName(String username) {
        return userDao.findByUserName(username).orElseThrow(() ->
                new EntityNotFoundException(UserErrorMessage.USER_NOT_FOUND_USERNAME));
    }

    public void updateUserById(Long id, UserUpdateDto userUpdateDto) {
        getUserByIdWithControl(id);
        checkUserNameAvailability(userUpdateDto.getUserName());
        User user = mapper.userUpdateDtoToUser(userUpdateDto);
        userDao.save(user);
    }

    public void deleteUserById(Long id) {
        User user = getUserByIdWithControl(id);
        userDao.delete(user);
    }

    private void checkUserNameAvailability(String userName) {
        if (userDao.existsAllByUserName(userName))
            throw new DuplicateEntityException(UserErrorMessage.HAS_DUPLICATE_USER_USERNAME);
    }
}
