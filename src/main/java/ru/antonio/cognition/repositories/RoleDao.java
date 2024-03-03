package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}
