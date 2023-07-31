package com.example.security.security;

import com.example.security.security.filter.JwtAuthenticationFilter;
import com.example.security.security.filter.JwtAuthorizationFilter;
import com.example.security.security.service.JwtUtilService;
import com.example.security.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtilService jwtUtilService;
    private final UserDetailsServiceImpl userService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtilService);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);

        // Por default ya es login - si se desea cambiar se debe modificar este parametro
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/auth/v1").permitAll();
                    auth.requestMatchers("/auth/v2").hasRole("ADMIN");
                    auth.requestMatchers("/auth/v3").hasAnyRole("USER","ADMIN");
                    auth.requestMatchers("/auth/v4").hasRole("USER");
                    auth.anyRequest().authenticated();
                });
        httpSecurity
                .sessionManagement(session ->
                   session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        httpSecurity
                .addFilter(jwtAuthenticationFilter);
        httpSecurity
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }

    @Bean
    public AuthenticationManager configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authenticationProvider())
                .build();
    }
}
