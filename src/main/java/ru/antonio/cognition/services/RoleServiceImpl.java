package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.repositories.RoleDao;

import java.util.List;
import java.util.Set;

/**
 * Класс упралвения бизнес-логикой сущности {@link Role}
 *
 * @author Antonio
 * @version 1.0
 */
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
        if(getRoleByName(nameRole) != null) {
            throw new RuntimeException("Данная роль существует. ");
        }
        return roleDao.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

    @Override
    public User assignRoleToUser(User user, String roleName) {
        Role role = getRoleByName(roleName);
        user.setRole(role);
        return user;
    }

    @Override
    public Role getRoleByName (String name) {
        return roleDao.findByName(name).orElse(null);
    }

    /**
     * Проверяет, существует ли такая роль в базе данных ролей. Метод лучше использовать при добавлении нового
     * пользователя, либо при добавлении роли.
     * @param role - роль, которую необходимо добавить.
     * @return - true - такая роль существует, false - если, роли в базе нет.
     */
    public boolean checkExistOfRole (Role role) {
        return getRoleByName(role.getName()) != null;
    }


}
