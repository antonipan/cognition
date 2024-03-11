package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Long> {

    Optional<Role> findByName (String name);
}
