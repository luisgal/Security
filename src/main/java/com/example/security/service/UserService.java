package com.example.security.service;

import com.example.security.core.exception.ResourceNotFound;
import com.example.security.service.jpa.entities.DulceriaUserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<DulceriaUserEntity> list();
    DulceriaUserEntity search(Long id) throws ResourceNotFound;
    void delete(Long id);
    void delete(DulceriaUserEntity dulceriaUserEntity);
    DulceriaUserEntity edit(DulceriaUserEntity dulceriaUserEntity);
    Optional<DulceriaUserEntity> findByName(String name);
}
