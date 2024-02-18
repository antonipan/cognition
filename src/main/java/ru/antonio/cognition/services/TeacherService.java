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

    /**
     * Сохраняет в базу данных добавленного учителя.
      * @param teacher - учитель
     * @return - учитель.
     */
    @TrackTeacherAction
    public Teacher saveTeacher (Teacher teacher) {
        return teachRepository.save(teacher);
    }

    /**
     * Сохраняет в базу данных список учителей.
     * @param teacherList - список учителей
     * @return - список учителей, который был сохранён.
     */
    @TrackTeacherAction
    public List <Teacher> saveAllTeacher (List<Teacher> teacherList) {
        return teachRepository.saveAll(teacherList);
    }

    /**
     * Получает полный список учителей из базы данных.
     * @return - полный список учителей.
     */
    @TrackTeacherAction
    public List <Teacher> getListTeachers () {
        return teachRepository.findAll();
    }

    /**
     * Получает список учителей из базы данных по их опыту работы
     * @param experience - опыт работы учителя
     * @return - лист учителей
     */
    @TrackTeacherAction
    public List <Teacher> getListByExperience(int experience) {
        return teachRepository.findByExperience(experience);
    }

    /**
     * Получает список учителей по учебной дисциплине (предмету)
     * @param subject - название предмета.
     * @return - список учителей, ведущих выбранный предмет.
     */
    @TrackTeacherAction
    public List<Teacher> getListBySubjects(String subject) {
        return teachRepository.findBySubject(subject);
    }

    /**
     * Получает одного учителя по его идентификационному номеру. Возвращает ноль,
     * если учителя нет в существующий базе или неверно набран номер.
     * @param id - запрашиваемый идентификационный номер.
     * @return - найденный по номеру учитель.
     */
    @TrackTeacherAction
    public Teacher getTeacherById(Long id) {
        return teachRepository.findById(id).orElseThrow();
    }

    /**
     * Обновляет опыт учителя по существующему id
     * @param id - идентификационный номер, по которому извлекается учитель из базы данных.
     * @param experience - новое знанчение опыта работы.
     * @return - обновлённый учитель.
     */
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
