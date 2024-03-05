package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.User;
import ru.antonio.cognition.repositories.UserDao;


@Service
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addNewUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userDao.save(newUser);
    }
}
