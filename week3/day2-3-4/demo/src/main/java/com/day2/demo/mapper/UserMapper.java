package com.day2.demo.mapper;

import com.day2.demo.dto.UserDto;
import com.day2.demo.dto.UserInputDto;
import com.day2.demo.model.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UserMapper {

    public static User mapInputDtoToEntity(UserInputDto inputDto){
        User user = new User();
        user.setUsername(inputDto.getUsername());
        user.setPassword(new String(Base64.getEncoder().encode(
                inputDto.getPassword().getBytes(StandardCharsets.UTF_8)
        )));
        user.setName(inputDto.getName());
        user.setSurname(inputDto.getSurname());
        return user;
    }

    public static UserDto mapEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getName() + " " + user.getSurname());
        return userDto;
    }
}
