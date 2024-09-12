package com.day2.demo.security;

import com.day2.demo.constant.ApiResponseCode;
import com.day2.demo.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    private static final String[] AUTH_WHITELIST = { //
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/products",
            "/products/**"
    };
    @Autowired
    SecurityConfig(UserDetailsService userDetailsService, ObjectMapper objectMapper) {
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
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
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers("/favorites", "/favorites/**", "/blacklists", "/blacklists/**").hasRole("USER")
                        .requestMatchers("/sellers", "/sellers/**").hasRole("ADMIN")
                        .requestMatchers("/users").hasAnyRole("ADMIN", "USER")
                )
                .exceptionHandling(customizer -> customizer
                        .accessDeniedHandler((req, resp, ex) -> {
                            ApiResponse<?> response = ApiResponse.error("Access Denied", ApiResponseCode.FORBIDDEN);
                            resp.setStatus(SC_FORBIDDEN);
                            resp.setContentType("application/json");
                            resp.getWriter().write(objectMapper.writeValueAsString(response));
                        })
                        .authenticationEntryPoint((req, resp, ex) -> {
                            ApiResponse<?> response = ApiResponse.error("Unauthorized", ApiResponseCode.UNAUTHORIZED);
                            resp.setStatus(SC_UNAUTHORIZED);
                            resp.setContentType("application/json");
                            resp.getWriter().write(objectMapper.writeValueAsString(response));
                        }))
                .formLogin(customizer -> customizer
                        .loginProcessingUrl("/login")
                        .successHandler((req, resp, auth) -> resp.setStatus(SC_OK))
                        .failureHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN)))
                        .sessionManagement(customizer -> customizer.invalidSessionStrategy((req, resp) -> resp.setStatus(SC_UNAUTHORIZED)))
                .logout(customizer -> customizer.logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
                .csrf(AbstractHttpConfigurer::disable);  // CSRF korumasını devre dışı bırakır (opsiyonel, eğer CSRF kullanmıyorsanız)
        return http.build();
    }
}
