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

    List<Teacher> findByExperience(int experience);

    List<Teacher> findByOrderByName();

    List<Teacher> findByOrderByExperience();

    List<Teacher> findByName(String name);


//    List<Teacher> findByOrderBySubject();
//
//    List<Teacher> findBySubject(String subject);

}
