package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.models.User;

import java.util.List;
/**
 * Interface упралвения бизнес-логикой сущности {@link User}
 *
 * @author Antonio
 * @version 1.0
 */
public interface UserService {

    void addNewUser (User user);

    List<User> getAllUser ();

    User getUserByName (String name);
}
