package com.day2.demo.service.impl;

import com.day2.demo.model.Role;
import com.day2.demo.repository.RoleRepository;
import com.day2.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> saveAllRoles(String... roleNames) {
        List<Role> roles = Arrays.stream(roleNames).map(Role::new).toList();
        return roleRepository.saveAll(roles);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllByNameIn(String... roleNames) {
        return roleRepository.findAllByNameIn(Arrays.asList(roleNames));
    }


}
