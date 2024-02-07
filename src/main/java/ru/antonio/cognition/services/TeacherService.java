package ru.antonio.cognition.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.model.Teacher;
import ru.antonio.cognition.repositories.TeachRepository;

import java.util.List;

@Service
public class TeacherService {

    private final TeachRepository teachRepository;

    public TeacherService(TeachRepository teachRepository) {
        this.teachRepository = teachRepository;
    }

    public Teacher saveTeacher (Teacher teacher) {
        return teachRepository.save(teacher);
    }

    public List <Teacher> saveAllTeacher (List<Teacher> teacherList) {
        return teachRepository.saveAll(teacherList);
    }

    public List <Teacher> getListTeachers () {
        return teachRepository.findAll();
    }

    public List <Teacher> getListByExperience(int experience) {
        return teachRepository.findByExperience(experience);
    }

    public List<Teacher> getListBySubjects(String subject) {
        return teachRepository.findBySubject(subject);
    }

    public Teacher getTeacherById(Long id) {
        return teachRepository.findById(id).orElseThrow();
    }

    public Teacher updateTeacherByExperience (Long id, int experience) {
        Teacher oldTeacher = teachRepository.findById(id).orElseThrow();
        oldTeacher.setExperience(experience);
        teachRepository.save(oldTeacher);
        return oldTeacher;
    }

    public void deleteById(Long id) {
        teachRepository.deleteById(id);
    }

    public List <Teacher> sortedTeachersByName () {
        return teachRepository.findByOrderByName();
    }

    public List<Teacher> sortedTeachersByExperience() {
        return teachRepository.findByOrderByExperience();
    }

    public List<Teacher> sortedTeachersBySubject() {
        return teachRepository.findByOrderBySubject();
    }
}
