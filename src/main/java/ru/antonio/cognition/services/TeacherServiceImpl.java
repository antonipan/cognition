package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.aspects.TrackTeacherAction;
import ru.antonio.cognition.repositories.TeacherDao;

import java.util.List;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;
    private SubjectService subjectService;

    @Autowired
    public TeacherServiceImpl (TeacherDao teacherDao,
                               SubjectService subjectService) {
        this.subjectService = subjectService;
        this.teacherDao = teacherDao;
    }

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
    @TrackTeacherAction
    public List<Teacher> getListTeachers () {
        return teacherDao.findAll();
    }

    /**
     * Получает список учителей из базы данных по их опыту работы
     * @param experience - опыт работы учителя
     * @return - лист учителей
     */
    @TrackTeacherAction
    public List <Teacher> getListByExperience(int experience) {
        return teacherDao.findByExperience(experience);
    }

//    /**
//     * Получает список учителей по учебной дисциплине (предмету)
//     * @param subject - название предмета.
//     * @return - список учителей, ведущих выбранный предмет.
//     */
//    @TrackTeacherAction
//    public List<Teacher> getListBySubjects(String subject) {
//        return teacherDao.findBySubject(subject);
//    }

    /**
     * Получает одного учителя по его идентификационному номеру. Возвращает ноль,
     * если учителя нет в существующий базе или неверно набран номер.
     * @param id - запрашиваемый идентификационный номер.
     * @return - найденный по номеру учитель.
     */
    @TrackTeacherAction
    public Teacher getTeacherById(Long id) {
        return teacherDao.findById(id).orElseThrow();
    }

//    /**
//     * Обновляет опыт учителя по существующему id
//     * @param id - идентификационный номер, по которому извлекается учитель из базы данных.
//     * @param experience - новое знанчение опыта работы.
//     * @return - обновлённый учитель.
//     */
//    @TrackTeacherAction
//    public Teacher updateTeacherByExperience (Long id, int experience) {
//        Teacher oldTeacher = teachRepository.findById(id).orElseThrow();
//        oldTeacher.setExperience(experience);
//        teachRepository.save(oldTeacher);
//        return oldTeacher;
//    }

    public void deleteById(Long id) {
        teacherDao.deleteById(id);
    }

    @TrackTeacherAction
    public List <Teacher> sortedTeachersByName () {
        return teacherDao.findByOrderByName();
    }

    @TrackTeacherAction
    public List<Teacher> sortedTeachersByExperience() {
        return teacherDao.findByOrderByExperience();
    }




}
