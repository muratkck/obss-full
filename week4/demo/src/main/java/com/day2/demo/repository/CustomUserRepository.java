package com.day2.demo.repository;

import com.day2.demo.model.User;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findByUsernameUsingCriteria(String username);

}
