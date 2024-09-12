package com.day2.demo.service;

import com.day2.demo.model.Role;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface RoleService {

    List<Role> saveAllRoles(String... roleNames);

    List<Role> findAllByNameIn(String... roleNames);
}
