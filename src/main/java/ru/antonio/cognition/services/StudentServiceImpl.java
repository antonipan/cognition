package ru.antonio.cognition.services;

import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.repositories.StudentDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Student> getStudentsByTeacherId(Long teacherId) {
        return new HashSet<>(studentDao.findStudentsByTeachersId(teacherId));
    }

    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public Student getStudentByName(String name) {
        return studentDao.findByName(name)
                .stream().findAny()
                .orElseThrow(() -> new NullPointerException("Not found"));
    }

    public Student getStudentById(Long studentId) {
        return studentDao.findById(studentId)
                .orElseThrow(() -> new NullPointerException("Not found"));
    }
}
