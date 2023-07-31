package com.example.security.service;

import com.example.security.core.exception.ResourceNotFound;
import com.example.security.service.jpa.entities.DulceriaRolesEntity;

import java.util.List;

public interface RolesService {
    DulceriaRolesEntity search(Long id) throws ResourceNotFound;
    List<DulceriaRolesEntity> list();
}
