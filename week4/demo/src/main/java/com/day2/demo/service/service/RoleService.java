package com.day2.demo.service.service;

import com.day2.demo.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> saveAllRoles(String... roleNames);

    List<Role> findAllByNameIn(String... roleNames);
}
