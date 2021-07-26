package ru.hw.hw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hw.hw.models.entitys.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByRole(String role);
}
