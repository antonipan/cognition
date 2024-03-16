package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.aspects.TrackTeacherAction;
import ru.antonio.cognition.repositories.TeacherDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс упралвения бизнес-логикой сущности {@link Teacher}
 *
 * @author Antonio
 * @version 1.0
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;
    private SubjectService subjectService;
    private QuestionnaireService questService;
    private StudentServiceImpl studentService;

    @Autowired
    public TeacherServiceImpl (TeacherDao teacherDao,
                               SubjectService subjectService,
                               QuestionnaireService questService,
                               StudentServiceImpl studentService) {
        this.subjectService = subjectService;
        this.teacherDao = teacherDao;
        this.questService = questService;
        this.studentService = studentService;
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

    public List<Questionnaire> updateTeacher(Long teacherId, Questionnaire questionnaire) {
        Teacher oldTeacher = teacherDao.findById(teacherId)
                .orElseThrow(() -> new NullPointerException("Not found"));
        oldTeacher.addQuestToAllQuestionnaires(questionnaire);
        teacherDao.save(oldTeacher);
        return questService.getQuestionnairesByTeacherId(teacherId);
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

    public Set <Subject> getAllSubjects() {
        return subjectService.getAllSubject();
    }

    public Subject createSubject (Subject subject) {
        return subjectService.createSubject(subject);
    }

    public Subject getSubjectById (Integer subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    public Questionnaire createQuestionnaire (Questionnaire questionnaire) {
        return questService.saveOneQuestionnaire(questionnaire);
    }



    public List<Questionnaire> getMyQuestionnaires(Long teacherId) {
        return questService.getQuestionnairesByTeacherId(teacherId);
    }


    public Questionnaire getQuestionnaireById(Long questId) {
        return questService.getQuestionnaireById(questId);
    }

    public Questionnaire updateMyQuestionnaire(Long questId, Questionnaire questionnaire) {
        Questionnaire oldQuest = getQuestionnaireById(questId);
        oldQuest.setQuestions(questionnaire.getQuestions());
        oldQuest.setName(questionnaire.getName());
        oldQuest.setShareCorrectAnswers(questionnaire.getShareCorrectAnswers());
        questService.saveOneQuestionnaire(oldQuest);
        return oldQuest;
    }

    public List<Questionnaire> deleteQuestFromMyList(Long teacherId, Long questId) {
        Teacher teacher = teacherDao.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        Questionnaire questionnaire = questService.getQuestionnaireById(questId);
        teacher.deleteQuestFromMyQuests(questionnaire);
        teacherDao.save(teacher);
        return questService.getQuestionnairesByTeacherId(teacherId);
    }


    public List<Questionnaire> getAllQuestionnaire() {
        return questService.getAllQuestionnaire();
    }

    public Set<Questionnaire> findQuestByName(String questName) {
        List<Questionnaire> quest =  questService.getAllQuestionnaire();
        return quest.stream().filter(q -> q.getName().contains(questName)).collect(Collectors.toSet());
    }

    public Set<Student> getMyStudents(Long teacherId) {
        return studentService.getStudentsByTeacherId(teacherId);
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public Set<Student> addStudentToList(Long teacherId, String name) {
        Teacher oldTeacher = getTeacherById(teacherId);
        Student student = studentService.getStudentByName(name);
        oldTeacher.addStudentToTeacher(student);
        teacherDao.save(oldTeacher);
        return getMyStudents(teacherId);
    }

    public List<Student> deleteStudentFromListTeacher(Long teacherId, Long studentId) {
        Teacher teacher = getTeacherById(teacherId);
        Student student = studentService.getStudentById(studentId);
        teacher.deleteStudentFromTeacher(student);
        teacherDao.save(teacher);
        return new ArrayList<>(getMyStudents(teacherId));
    }

    public void setQuestForStudent(Long questId, Long studentId) {
        Questionnaire questionnaire = questService.getQuestionnaireById(questId);
        Student student = studentService.getStudentById(studentId);
        student.addNotPass(questionnaire);
        studentService.saveStudent(student);
    }

    public List <Teacher> getTeacherByName(String name) {
        return teacherDao.findByName(name);
    }
}
