package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
