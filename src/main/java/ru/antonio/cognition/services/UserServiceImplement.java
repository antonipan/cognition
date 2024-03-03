package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.repositories.RoleDao;
import ru.antonio.cognition.repositories.UserDao;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link UserService} interface.
 * @author Antonio
 * @version 1.0
 */
@Service
public class UserServiceImplement implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set < Role> roles = new HashSet<>();
        roles.add(roleDao.findById(1L).orElse(null));
        user.setRoleSet(roles);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
