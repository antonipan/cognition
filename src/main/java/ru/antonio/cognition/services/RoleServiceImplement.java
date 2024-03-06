package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.repositories.RoleDao;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImplement implements RoleService{

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImplement(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Set<Role> getRoles() {
        return new HashSet<>(roleDao.findAll());
    }

    // TODO: 06.03.2024 Доделать метод по назначению роли 
    @Override
    public User assignRoleToUser(User user) {
        return null;
    }

    // TODO: 06.03.2024 Получить роль по ID 
}
