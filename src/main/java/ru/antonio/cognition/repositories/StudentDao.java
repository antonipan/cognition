package ru.antonio.cognition.repositories;

import org.springframework.stereotype.Repository;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.User;

import java.util.List;

@Repository
public interface StudentDao extends UserDao<Student> {

    List<Student> findStudentsByTeachersId (Long id);
}
