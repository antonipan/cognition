package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Role;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {

    public List <Role> findByName (String name);
}
