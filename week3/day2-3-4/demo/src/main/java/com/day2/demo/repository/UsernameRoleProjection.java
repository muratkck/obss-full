package com.day2.demo.repository;


import org.springframework.beans.factory.annotation.Value;

public interface UsernameRoleProjection {

    @Value("#{target.username}")
    String getUsername();

    String getRole();

    String getFullName();
}
