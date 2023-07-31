package com.example.security.service.jpa;

import com.example.security.core.exception.ResourceNotFound;
import com.example.security.service.RolesService;
import com.example.security.service.jpa.entities.DulceriaRolesEntity;
import com.example.security.service.jpa.repositories.DulceriaRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final DulceriaRolesRepository rolesRepository;

    @Override
    public DulceriaRolesEntity search(Long id) throws ResourceNotFound {
        return rolesRepository.findById(id).orElseThrow(() -> new ResourceNotFound("No se encontró ningun Rol"));
    }

    @Override
    public List<DulceriaRolesEntity> list() {
        return rolesRepository.findAll();
    }
}
