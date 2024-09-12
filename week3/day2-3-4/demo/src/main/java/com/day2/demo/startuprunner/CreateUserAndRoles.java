package com.day2.demo.startuprunner;

import com.day2.demo.dto.UserInputDto;
import com.day2.demo.model.Role;
import com.day2.demo.model.User;
import com.day2.demo.service.RoleService;
import com.day2.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateUserAndRoles implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public CreateUserAndRoles(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // create user and admin roles
        roleService.saveAllRoles("ADMIN", "USER");
        userService.saveDefaultUsers();


    }
}
