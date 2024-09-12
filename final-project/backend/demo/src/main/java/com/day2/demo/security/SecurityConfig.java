package com.day2.demo.security;

import com.day2.demo.constant.ApiResponseCode;
import com.day2.demo.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static jakarta.servlet.http.HttpServletResponse.*;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint handler;

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
            "/auth/**"
    };
    @Autowired
    SecurityConfig(UserDetailsService userDetailsService, ObjectMapper objectMapper, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
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
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
    */
    @Bean
    @Autowired
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers(AUTH_WHITELIST).permitAll()
                    .requestMatchers("/sellers", "/sellers/**").hasRole("ADMIN")
                    .requestMatchers("/favorites", "/favorites/**", "/blacklists", "/blacklists/**").hasRole("USER")
                    .requestMatchers("/users", "/products", "/sellers").hasAnyRole("ADMIN", "USER")
                    .anyRequest().authenticated()
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
            .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .logout(customizer -> customizer.logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
            .csrf(AbstractHttpConfigurer::disable);  // CSRF korumasını devre dışı bırakır (opsiyonel, eğer CSRF kullanmıyorsanız)
            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /*
    @Bean
    @Autowired
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
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
    */
}
