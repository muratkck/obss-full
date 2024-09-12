package com.day2.demo.mapper;

import com.day2.demo.dto.response.UserDto;
import com.day2.demo.dto.input.UserInputDto;
import com.day2.demo.model.Role;
import com.day2.demo.model.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.stream.Collectors;

public class UserMapper {

    public static User mapInputDtoToEntity(UserInputDto inputDto){
        User user = new User();
        user.setUsername(inputDto.getUsername());
        user.setPassword(inputDto.getPassword());
        user.setName(inputDto.getName());
        user.setSurname(inputDto.getSurname());
        return user;
    }

    public static UserDto mapEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getName() + " " + user.getSurname());
        userDto.setRoles(user.getRoles().stream().map(Role::getName).toList());
        return userDto;
    }
}
