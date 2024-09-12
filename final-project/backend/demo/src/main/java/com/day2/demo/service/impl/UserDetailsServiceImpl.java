package com.day2.demo.service.impl;

import com.day2.demo.model.User;
import com.day2.demo.repository.UserRepository;
import com.day2.demo.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findUserById(id).
                orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return JwtUserDetails.create(user);
    }
}
