package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.User;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
