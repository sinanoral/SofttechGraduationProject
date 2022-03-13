package com.softtech.mapper;

import com.softtech.model.entity.User;
import com.softtech.model.requestDto.UserCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userCreateDtoToUser(UserCreateDto userCreateDto);
}
