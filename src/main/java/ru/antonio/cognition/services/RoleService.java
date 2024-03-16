package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;

import java.util.List;
/**
 * Интерфейс упралвения бизнес-логикой сущности {@link Questionnaire}
 *
 * @author Antonio
 * @version 1.0
 */
public interface RoleService {

    Role saveRole (Role role);

    List<Role> getRoles ();

    User assignRoleToUser (User user, String roleName);

    Role getRoleByName (String name);
}
