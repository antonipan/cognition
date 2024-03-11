package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ru.antonio.cognition.models.User;

import java.util.Optional;

@Repository
public interface UserDao <T extends User> extends JpaRepository<T, Long> {

    Optional<User> findByUsername(String username);


}
