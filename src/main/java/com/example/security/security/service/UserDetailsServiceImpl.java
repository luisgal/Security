package com.example.security.security.service;

import com.example.security.service.UserService;
import com.example.security.service.jpa.entities.DulceriaUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        DulceriaUserEntity userEntity = userService.findByName(username).orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        return User
                .withUsername(userEntity.getName())
                .password(userEntity.getPassword())
                .roles(userEntity
                        .getRoles()
                        .stream()
                        .map(role -> role.getRol().getName())
                        .toArray(String[]::new)
                )
                .build();
    }
}
