package com.day2.demo.service.service;

import com.day2.demo.dto.response.UserDto;
import com.day2.demo.dto.input.UserInputDto;
import com.day2.demo.dto.search.UserSearchInputDto;
import com.day2.demo.dto.update.UserUpdateDto;
import com.day2.demo.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

public interface UserService {

    UserDto saveUser(UserInputDto userInputDto);

    Page<UserDto> findAllUsersBySearch(UserSearchInputDto searchInputDto);

    //UserDto findUserByUsername(String username);

    void saveDefaultUsers();

    User findUserById(Long userId);

    User findByUsername(String username);

    void deleteUserById(Long userId);

    UserDto updateUser(UserUpdateDto updateDto);
}
