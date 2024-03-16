package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Role;

import java.util.List;
import java.util.Optional;
/**
 * Класс общарения к хранилищу сущности {@link Role}
 *
 * @author Antonio
 * @version 1.0
 */
public interface RoleDao extends JpaRepository<Role, Long> {
// TODO: 16.03.2024 дописать документацию

    Optional<Role> findByName (String name);
}
