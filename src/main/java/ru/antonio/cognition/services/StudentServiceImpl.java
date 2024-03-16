package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.repositories.StudentDao;
import ru.antonio.cognition.repositories.TeacherDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс упралвения бизнес-логикой сущности {@link Student}
 *
 * @author Antonio
 * @version 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private TeacherDao teacherDao;
    private QuestionnaireService questionnaireService;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao,
                              TeacherDao teacherDao,
                              QuestionnaireService questionnaireService) {
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.questionnaireService = questionnaireService;
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

    public Student updateStudent(Long id, Student student1) {
        Student oldStudent = studentDao.findById(id)
                .orElseThrow(() -> new NullPointerException("Not found"));
        oldStudent.setName(student1.getName());
        oldStudent.setGrade(student1.getGrade());
        oldStudent.setNotPassable(student1.getNotPassable());
        oldStudent.setPassable(student1.getPassable());
        return studentDao.save(oldStudent);
    }

    public List<Teacher> findTeacherByName(String name) {
        return teacherDao.findByName(name);
    }

    public Questionnaire showQuestionnaire(Long questId) {
        return questionnaireService.getQuestionnaireById(questId);
    }

    public void passTesting(Long studentId, Long questId, Questionnaire questFromStudent) {
        // Получаем студента из репозитория
        Student student = studentDao.findById(studentId)
                .orElseThrow(() -> new NullPointerException("Not found"));
        // Проверяем, находится ли методика в очереди методик на прохождение.
        if(!student.getNotPassable()
                .contains(questionnaireService
                        .getQuestionnaireById(questId))) {
            throw new RuntimeException("You cannot pass this test");
        }
        // Получаем методику из базы данных.
        Questionnaire questFromDB = questionnaireService.getQuestionnaireById(questId);
        // Сравниваем ответы и получаем балл прохождения.
        double totalBall = counterCorrectAnswer(questFromDB, questFromStudent);
        if(totalBall < questFromDB.getShareCorrectAnswers()) {
            throw new RuntimeException("Вы не прошли тест");
        }
        // Перекидываем методику в коллекцию пройденных методик.
        student.deleteNotPassAndAddPass(questFromDB);
        studentDao.save(student);
    }


    // TODO: 14.03.2024 После преобразования коллекции вопросов изменить метод.
    private double counterCorrectAnswer(Questionnaire questDB, Questionnaire questUser) {
        double counterAnswer = 0;
        for (int i = 0; i < questDB.getQuestions().size(); i++) {
            if(questUser.getQuestions().get(i).equals(questDB.getQuestions().get(i))) {
                // Предполагается, что здесь будет прибавляться вес вопроса,
                // но так как это просто лист строк, то даётся один балл за совпадение.
                counterAnswer += 1;
            }
        }
        return counterAnswer;
    }
}
