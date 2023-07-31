package com.example.security.service.jpa.repositories;

import com.example.security.service.jpa.entities.DulceriaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DulceriaUserRepository extends JpaRepository<DulceriaUserEntity,Long> {
    Optional<DulceriaUserEntity> findByName(String name);
}
