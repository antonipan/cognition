package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role saveRole (Role role);

    List<Role> getRoles ();

    User assignRoleToUser (User user);

    Set <Role> getRolesByName (String name);
}
