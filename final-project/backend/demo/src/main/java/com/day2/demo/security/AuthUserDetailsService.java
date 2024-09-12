package com.day2.demo.security;

import com.day2.demo.model.User;
import com.day2.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
//@Transactional
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        AuthUserDetails details = new AuthUserDetails();
        details.setId(user.getId());
        details.setFullName(user.getName() + " " + user.getSurname());
        details.setUsername(user.getUsername());
        details.setPassword(user.getPassword());
        details.setEnabled(user.getIsActive());
        details.setAuthorities(user.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority("ROLE_" + x.getName()))
                .toList());

        return details;

    }
}
