package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;

import java.util.Set;

public interface RoleService {

    Role saveRole (Role role);

    Set<Role> getRoles ();

    User assignRoleToUser (User user);

}
