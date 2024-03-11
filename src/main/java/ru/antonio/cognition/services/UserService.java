package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.models.User;

import java.util.List;

public interface UserService {

    void addNewUser (User user);

    List<User> getAllUser ();

    User getUserByName (String name);
}
