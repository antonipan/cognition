package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.aspects.TrackTeacherAction;
import ru.antonio.cognition.repositories.TeacherDao;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;
    private SubjectService subjectService;
    private QuestionnaireService questService;

    @Autowired
    public TeacherServiceImpl (TeacherDao teacherDao,
                               SubjectService subjectService,
                               QuestionnaireService questService) {
        this.subjectService = subjectService;
        this.teacherDao = teacherDao;
        this.questService = questService;
    }

    //РАБОТА С УЧИТЕЛЯМИ
    /**
     * Сохраняет в базу данных добавленного учителя.
      * @param teacher - учитель
     * @return - учитель.
     */
    public Teacher saveTeacher (Teacher teacher) {
        return teacherDao.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher newTeacher) {
        Teacher oldTeacher = teacherDao.findById(id)
                .orElseThrow(() -> new NullPointerException("Such newTeacher not found. "));
        oldTeacher.setExperience(newTeacher.getExperience());
        oldTeacher.setSubjects(newTeacher.getSubjects());
        oldTeacher.setName(newTeacher.getName());
        return oldTeacher;
    }

    public Teacher updateTeacher(Long id, Subject subject) {
        Teacher oldTeacher = teacherDao.findById(id)
                .orElseThrow(() -> new NullPointerException("Such newTeacher not found. "));
        oldTeacher.addSubjectToSubjects(subject);
        teacherDao.save(oldTeacher);
        return oldTeacher;
    }

    /**
     * Сохраняет в базу данных список учителей.
     * @param teacherList - список учителей
     * @return - список учителей, который был сохранён.
     */
    @TrackTeacherAction
    @Override
    public List <Teacher> saveAllTeacher (List<Teacher> teacherList) {
        return teacherDao.saveAll(teacherList);
    }

    /**
     * Получает полный список учителей из базы данных.
     * @return - полный список учителей.
     */
    public List<Teacher> getListTeachers () {
        return teacherDao.findAll();
    }


    // РАБОТА С ПРЕДМЕТАМИ

    public List <Subject> getMySubjects(Long id) {
        return subjectService.getSubjectsByTeacherId(id);
    }

    public void deleteSubjectFromTeacherList (Long teacherId, Integer subjectId) {
        Teacher teacher = teacherDao.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        Subject subject = subjectService.getSubjectById(subjectId);
        teacher.deleteSubject(subject);
        teacherDao.save(teacher);
    }

    /**
     * Получает одного учителя по его идентификационному номеру. Возвращает ноль,
     * если учителя нет в существующий базе или неверно набран номер.
     * @param id - запрашиваемый идентификационный номер.
     * @return - найденный по номеру учитель.
     */
    public Teacher getTeacherById(Long id) {
        return teacherDao.findById(id).orElseThrow();
    }

    public void deleteTeacherById(Long id) {
        teacherDao.deleteById(id);
    }

}
