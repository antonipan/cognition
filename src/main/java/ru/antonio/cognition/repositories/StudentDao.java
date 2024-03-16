package ru.antonio.cognition.repositories;

import org.springframework.stereotype.Repository;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Класс общарения к хранилищу сущности {@link Student}
 *
 * @author Antonio
 * @version 1.0
 */
@Repository
public interface StudentDao extends UserDao<Student> {

    List<Student> findStudentsByTeachersId (Long id);

    List<Student> findByName(String username);
}
