package com.day2.demo.service;

import com.day2.demo.dto.UserDto;
import com.day2.demo.dto.UserInputDto;
import com.day2.demo.dto.UserSearchInputDto;
import com.day2.demo.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserInputDto userInputDto);

    Page<UserDto> findAllUsers(UserSearchInputDto searchInputDto);

    //UserDto findUserByUsername(String username);

    void saveDefaultUsers();
}
