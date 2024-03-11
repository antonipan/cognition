package ru.antonio.cognition.services;

import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.repositories.StudentDao;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void saveStudent(Student student) {
        studentDao.save(student);
    }
}
