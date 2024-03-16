package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.repositories.SubjectDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс упралвения бизнес-логикой сущности {@link Subject}
 *
 * @author Antonio
 * @version 1.0
 */
@Service
public class SubjectService {

    private SubjectDao subjectDao;

    @Autowired
    public SubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public Subject createSubject (Subject subject) {
        return subjectDao.save(subject);
    }

    public void deleteSubject (Integer id) {
        subjectDao.deleteById(id);
    }

    public Set <Subject> getAllSubject () {
        return new HashSet<>(subjectDao.findAll());
    }

    public Subject getSubjectById (Integer id) {
        return subjectDao.findById(id)
                .orElseThrow(() -> new NullPointerException("Such subject not exists... "));
    }

    public List<Subject> getSubjectsByTeacherId (Long teacherId) {
        return subjectDao.findSubjectsByTeachersId(teacherId);
    }
}
