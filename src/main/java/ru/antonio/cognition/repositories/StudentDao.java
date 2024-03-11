package ru.antonio.cognition.repositories;

import org.springframework.stereotype.Repository;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.User;

@Repository
public interface StudentDao extends UserDao<Student> {
}
