package ru.antonio.cognition.services;

import org.springframework.stereotype.Service;
import ru.antonio.cognition.aspects.TrackTeacherAction;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.repositories.TeachRepository;

import java.util.List;

@Service
public class TeacherService {

    private final TeachRepository teachRepository;

    public TeacherService(TeachRepository teachRepository) {
        this.teachRepository = teachRepository;
    }

    @TrackTeacherAction
    public Teacher saveTeacher (Teacher teacher) {
        return teachRepository.save(teacher);
    }

    @TrackTeacherAction
    public List <Teacher> saveAllTeacher (List<Teacher> teacherList) {
        return teachRepository.saveAll(teacherList);
    }

    @TrackTeacherAction
    public List <Teacher> getListTeachers () {
        return teachRepository.findAll();
    }

    @TrackTeacherAction
    public List <Teacher> getListByExperience(int experience) {
        return teachRepository.findByExperience(experience);
    }

    @TrackTeacherAction
    public List<Teacher> getListBySubjects(String subject) {
        return teachRepository.findBySubject(subject);
    }

    @TrackTeacherAction
    public Teacher getTeacherById(Long id) {
        return teachRepository.findById(id).orElseThrow();
    }

    @TrackTeacherAction
    public Teacher updateTeacherByExperience (Long id, int experience) {
        Teacher oldTeacher = teachRepository.findById(id).orElseThrow();
        oldTeacher.setExperience(experience);
        teachRepository.save(oldTeacher);
        return oldTeacher;
    }

    public void deleteById(Long id) {
        teachRepository.deleteById(id);
    }

    @TrackTeacherAction
    public List <Teacher> sortedTeachersByName () {
        return teachRepository.findByOrderByName();
    }

    @TrackTeacherAction
    public List<Teacher> sortedTeachersByExperience() {
        return teachRepository.findByOrderByExperience();
    }

    @TrackTeacherAction
    public List<Teacher> sortedTeachersBySubject() {
        return teachRepository.findByOrderBySubject();
    }
}
