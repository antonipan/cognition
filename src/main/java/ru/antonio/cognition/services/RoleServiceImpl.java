package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.repositories.RoleDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService{


    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;

    }

    @Override
    public Role saveRole(Role role) {
        String nameRole = role.getName();
        if(!getRolesByName(nameRole).isEmpty()) {
            throw new RuntimeException("Данная роль существует. ");
        }
        return roleDao.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

    @Override
    public User assignRoleToUser(User user) {
        String roleUser = user.getRole().getName();
        Role role;
        if(roleUser.equalsIgnoreCase("student")) {
            role = getRolesByName(roleUser).stream().findFirst().orElseThrow();
            user.setRole(role);
        } else if (roleUser.equalsIgnoreCase("teacher")) {
            role = getRolesByName(roleUser).stream().findFirst().orElseThrow();
            user.setRole(role);
        } else if (roleUser.equalsIgnoreCase("admin")) {
            role = getRolesByName(roleUser).stream().findFirst().orElseThrow();
            user.setRole(role);
        }
        return user;
    }

    @Override
    public Set <Role> getRolesByName (String name) {
        return new HashSet<>(roleDao.findByName(name));
    }

    /**
     * Проверяет, существует ли такая роль в базе данных ролей. Метод лучше использовать при добавлении нового
     * пользователя, либо при добавлении роли.
     * @param role - роль, которую необходимо добавить.
     * @return - true - такая роль существует, false - если, роли в базе нет.
     */
    public boolean checkExistOfRole (Role role) {
        Set <Role> setRole = getRolesByName(role.getName());
        return !setRole.isEmpty();
    }


}
