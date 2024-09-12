package com.day2.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import static jakarta.servlet.http.HttpServletResponse.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Autowired
    SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Autowired
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder encoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Bean
    @Autowired
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(customizer -> customizer
                        //.requestMatchers(AUTH_WHITELIST).anonymous().anyRequest()
                        //.anyRequest().permitAll());
                        .anyRequest()
                        .hasAnyRole("ADMIN", "USER"))
                .exceptionHandling(customizer -> customizer
                        .accessDeniedHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN))
                        .authenticationEntryPoint((req, resp, ex) -> resp.setStatus(SC_UNAUTHORIZED)))
                .formLogin(customizer -> customizer
                        .loginProcessingUrl("/login")
                        .successHandler((req, resp, auth) -> resp.setStatus(SC_OK))
                        .failureHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN)))
                .sessionManagement(customizer -> customizer.invalidSessionStrategy((req, resp) -> resp.setStatus(SC_UNAUTHORIZED)))
                .logout(customizer -> customizer.logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
