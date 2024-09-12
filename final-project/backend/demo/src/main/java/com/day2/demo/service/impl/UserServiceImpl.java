package com.day2.demo.service.impl;

import com.day2.demo.dto.response.UserDto;
import com.day2.demo.dto.input.UserInputDto;
import com.day2.demo.dto.search.UserSearchInputDto;
import com.day2.demo.dto.update.UserUpdateDto;
import com.day2.demo.exception.ConflictException;
import com.day2.demo.mapper.UserMapper;
import com.day2.demo.model.Role;
import com.day2.demo.model.User;
import com.day2.demo.repository.UserRepository;
import com.day2.demo.service.service.RoleService;
import com.day2.demo.service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public UserDto saveUser(UserInputDto userInputDto) {

        Map<String, Role> roleMap = roleService.findAllByNameIn("ADMIN", "USER")
                .stream().collect(Collectors.toMap(Role::getName, Function.identity()));



        User user = UserMapper.mapInputDtoToEntity(userInputDto);
        var userRole = roleMap.get("USER");
        user.getRoles().add(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRole.getUsers().add(user);

        return UserMapper.mapEntityToDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAllUsersBySearch(UserSearchInputDto searchInputDto) {
        Pageable pageable = PageRequest.of(searchInputDto.getPage(), searchInputDto.getSize());

        Page<User> foundUsers = userRepository.findAllBySearch(searchInputDto, pageable);

        return foundUsers.map(UserMapper::mapEntityToDto);
    }

    @Override
    @Transactional
    public void saveDefaultUsers(){
        // Function.identity() ne işe yarıyor?
        Map<String, Role> roleMap = roleService.findAllByNameIn("ADMIN", "USER")
                .stream().collect(Collectors.toMap(Role::getName, Function.identity()));
        

        User userAdmin = new User();
        userAdmin.setUsername("murat.admin");
        userAdmin.setPassword(passwordEncoder.encode("root"));
        userAdmin.setName("Murat");
        userAdmin.setSurname("Kucuk");
        userAdmin.setIsActive(true);
        var adminRole = roleMap.get("ADMIN");
        userAdmin.getRoles().add(adminRole);
        adminRole.getUsers().add(userAdmin);

        User user = new User();
        user.setUsername("murat.user");
        user.setPassword(passwordEncoder.encode("root"));
        user.setName("Murat");
        user.setSurname("Kucuk");
        user.setIsActive(true);
        var userRole = roleMap.get("USER");
        user.getRoles().add(userRole);
        userRole.getUsers().add(user);

        User user2 = new User();
        user2.setUsername("murat.user2");
        user2.setPassword(passwordEncoder.encode("root"));
        user2.setName("Murat");
        user2.setSurname("Kucuk");
        user2.setIsActive(true);
        user2.getRoles().add(userRole);
        userRole.getUsers().add(user2);

        userRepository.saveAll(List.of(userAdmin, user, user2));
    }

    @Override
    public User findUserById(Long userId) {
        Optional<User> user = userRepository.findUserById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new EntityNotFoundException("User not found");
    }

    @Override
    public User findByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new EntityNotFoundException("User not found");
    }

    @Override
    public void deleteUserById(Long userId) {
        Optional<User> userOptional = userRepository.findUserById(userId);

        if(userOptional.isPresent()){

            User user = userOptional.get();

            for (Role role: new HashSet<>(user.getRoles())) {
                user.removeRole(role);
            }

            userRepository.deleteById(userId);
            return;
        }
        throw new EntityNotFoundException("Seller with id " + userId + " does not exist!");
    }

    @Override
    @Transactional
    public UserDto updateUser(UserUpdateDto updateDto) {
        // Fetch the existing user from the database

        Optional<User> optionalUser = userRepository.findUserById(updateDto.getId());

        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + updateDto.getId() + " does not exist!");
        }

        Optional<User> isExistingUsername = userRepository.findByUsername(updateDto.getUsername());

        if (isExistingUsername.isPresent()) {
            throw new ConflictException("Existed username");
        }
        User existedUser = optionalUser.get();

        if(updateDto.getUsername() != null){
            existedUser.setUsername(updateDto.getUsername());
        }

        // Save the updated user back to the database
        User updatedUser = userRepository.save(existedUser);

        // Convert updated user to UserDto
        return UserMapper.mapEntityToDto(updatedUser);
    }

}
