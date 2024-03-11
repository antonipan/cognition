package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;

import java.util.List;

public interface RoleService {

    Role saveRole (Role role);

    List<Role> getRoles ();

    User assignRoleToUser (User user, String roleName);

    Role getRoleByName (String name);
}
