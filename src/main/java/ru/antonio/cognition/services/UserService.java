package ru.antonio.cognition.services;

import ru.antonio.cognition.models.User;

/**
 * Service Class for {@link ru.antonio.cognition.models.User}
 * @author Antonio
 * @version 1.0
 */
public interface UserService {

    void save (User user);

    User findByUsername (String username);
}
