package com.day2.demo.startuprunner;

import com.day2.demo.service.service.ProductService;
import com.day2.demo.service.service.RoleService;
import com.day2.demo.service.service.SellerService;
import com.day2.demo.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateUserAndRoles implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final ProductService productService;
    private final SellerService sellerService;

    @Autowired
    public CreateUserAndRoles(UserService userService, RoleService roleService, ProductService productService, SellerService sellerService) {
        this.userService = userService;
        this.roleService = roleService;
        this.productService = productService;
        this.sellerService = sellerService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // create user and admin roles
        roleService.saveAllRoles("ADMIN", "USER");
        userService.saveDefaultUsers();
        sellerService.saveDefaultSellers();
        productService.saveDefaultProducts();


    }
}
