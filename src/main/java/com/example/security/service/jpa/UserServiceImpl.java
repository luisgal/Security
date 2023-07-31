package com.example.security.service.jpa;

import com.example.security.core.exception.ResourceNotFound;
import com.example.security.service.UserService;
import com.example.security.service.jpa.entities.DulceriaUserEntity;
import com.example.security.service.jpa.repositories.DulceriaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final DulceriaUserRepository userRepository;

    @Override
    public List<DulceriaUserEntity> list() {
        return userRepository.findAll();
    }

    @Override
    public DulceriaUserEntity search(Long id) throws ResourceNotFound {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("No se encontró ningún Usuario con el id: " + id));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(DulceriaUserEntity dulceriaUserEntity) {
        userRepository.delete(dulceriaUserEntity);
    }

    @Override
    public DulceriaUserEntity edit(DulceriaUserEntity dulceriaUserEntity) {
        return userRepository.save(dulceriaUserEntity);
    }

    @Override
    public Optional<DulceriaUserEntity> findByName(String name) {
        return userRepository.findByName(name);
    }
}
