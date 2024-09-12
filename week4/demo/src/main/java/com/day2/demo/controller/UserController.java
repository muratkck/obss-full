package com.day2.demo.controller;

import com.day2.demo.dto.ApiResponse;
import com.day2.demo.dto.response.UserDto;
import com.day2.demo.dto.input.UserInputDto;
import com.day2.demo.dto.search.UserSearchInputDto;
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

@Tag(name = "Users", description = "Controls the requests that comes to the /users")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ApiResponse<UserDto> saveUser(@Valid @RequestBody UserInputDto userInputDto) {
        return ApiResponse.success(userService.saveUser(userInputDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ApiResponse<Page<UserDto>> findAllUsersBySearch(UserSearchInputDto searchInputDto) {
        return ApiResponse.success(userService.findAllUsersBySearch(searchInputDto));
    }

    /*
    @Operation(summary = "Returns the user whose username equals to the given username",
            description = "Returns the user whose username equals to the given username." +
                    " We can do detailed explanation here. Also for parameters and responses!"
    )
    //Operation ile methodun açıklamasını ve detaylı açıklamasını yapabiliriz. Ne işe yaradığını ve nasıl kullanılacağını belirtebiliriz.
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Returns the user and null message"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "User could not found", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Server failed", content = @Content)
    })
    //Api responses ile hata veya başarılı durumlar için açıklama yapabiliriz. Content ile de dönecek olan response'un içeriğini belirtebiliriz.
    //Aynı zamanda response code ile de dönecek olan response'un http status code'unu belirtebiliriz.
    @GetMapping("/{username}")
    public ApiResponse<UserDto> findUserByUsername(@PathVariable String username) {
        // if userService cannot found the user, still returns status code 200! REVISE it
        return ApiResponse.of(userService.findUserByUsername(username));
    }
     */

//    @PutMapping("/{username}")
//    public ApiResponse<?> updateUser(@PathVariable String username,
//                              @RequestBody UserDto userDto) {
//        if(!userDtoMap.containsKey(username)) {
//            return ApiResponse.of("User not found!");
//        }
//        UserDto existingUser = userDtoMap.get(username);
//        existingUser.setUsername(userDto.getUsername());
//        existingUser.setPassword(userDto.getPassword());
//        return ApiResponse.of(existingUser);
//    }
//
//    @PutMapping("/update-all-password")
//    public ApiResponse<?> updateUsersPassword(@RequestParam String password) {
//        userDtoMap.values().forEach(userDto -> userDto.setPassword(password));
//        return ApiResponse.ofCode(ApiResponseCode.OK);
//    }
//
//    @DeleteMapping("/{username}")
//    public ApiResponse<?> deleteUser(@PathVariable String username) {
//        userDtoMap.remove(username);
//        return ApiResponse.ofCode(ApiResponseCode.OK);
//    }
}
