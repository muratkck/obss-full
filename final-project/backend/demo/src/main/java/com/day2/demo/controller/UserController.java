package com.day2.demo.controller;

import com.day2.demo.dto.ApiResponse;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.response.UserDto;
import com.day2.demo.dto.input.UserInputDto;
import com.day2.demo.dto.search.UserSearchInputDto;
import com.day2.demo.dto.update.ProductUpdateDto;
import com.day2.demo.dto.update.UserUpdateDto;
import com.day2.demo.mapper.ProductMapper;
import com.day2.demo.mapper.UserMapper;
import com.day2.demo.service.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAnyRole('ADMIN')")
@Tag(name = "Users", description = "Controls the requests that comes to the /users")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<UserDto> saveUser(@Valid @RequestBody UserInputDto userInputDto) {
        return ApiResponse.success(userService.saveUser(userInputDto));
    }

    @GetMapping
    public ApiResponse<Page<UserDto>> findAllUsersBySearch(UserSearchInputDto searchInputDto) {
        return ApiResponse.success(userService.findAllUsersBySearch(searchInputDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDto> findProductById(@PathVariable Long id) {
        UserDto userDto = UserMapper.mapEntityToDto(userService.findUserById(id));
        return ApiResponse.success(userDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ApiResponse.success("User is deleted successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto updateDto) {
        updateDto.setId(id); // DTO'ya ID'yi set et
        UserDto userDto = userService.updateUser(updateDto);
        return ApiResponse.success(userDto);
    }
}
