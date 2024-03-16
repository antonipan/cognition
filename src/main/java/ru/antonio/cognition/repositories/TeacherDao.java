package ru.antonio.cognition.repositories;

import org.springframework.stereotype.Repository;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.models.User;

import java.util.List;

/**
 * Класс общарения к хранилищу сущности {@link Teacher}
 *
 * @author Antonio
 * @version 1.0
 */
@Repository
public interface TeacherDao extends UserDao<Teacher> {
// TODO: 16.03.2024 дописать документацию

    List<Teacher> findByName(String name);


}
