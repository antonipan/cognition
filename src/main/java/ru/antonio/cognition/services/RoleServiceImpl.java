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

    /**
     * Хранилище сущности Роль {@link Role}
     */
    private final RoleDao roleDao;

    /**
     *
     * @param roleDao - Хранилище доступа к базе ролей
     */
    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;

    }

    /**
     * сохраняет роль в базу данных
     * Если роль с таким именем есть в базе, то выбрасывается исключение
     * @param role - сохраняемая роль
     * @return - сохранённая роль
     */
    @Override
    public Role saveRole(Role role) {
        String nameRole = role.getName();
        if(getRoleByName(nameRole) != null) {
            throw new RuntimeException("Данная роль существует. ");
        }
        return roleDao.save(role);
    }

    /**
     * получает список всех ролей
     * @return - список полученных ролей
     */
    @Override
    public List<Role> getRoles() {
        return roleDao.findAll();
    }

    /**
     * назначает роль определённому пользователю
     * @param user - пользователь, которому необходимо назначить роль
     * @param roleName - имя новой роли
     * @return - пользователь с новой ролью
     */
    @Override
    public User assignRoleToUser(User user, String roleName) {
        Role role = getRoleByName(roleName);
        user.setRole(role);
        return user;
    }

    /**
     * получает роль по её имени. Если роли нет возвращает пустой объект
     * @param name - имя роли, которую требуется получить
     * @return - найденная полученная роль
     */
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
