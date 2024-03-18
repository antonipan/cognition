package ru.antonio.cognition.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Role;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.repositories.StudentDao;
import ru.antonio.cognition.repositories.TeacherDao;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentServiceTest {

    @Mock
    private StudentDao studentDao;

    @Mock
    private TeacherDao teacherDao;

    @Mock
    private QuestionnaireService questionnaireService;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student1;
    private Student student2;
    private Student student3;
    private List<Student> studentList;

    @BeforeEach
    void setup () {
        student1 = new Student();
        student2 = new Student("irina", "1234", new Role("user"));
        student3 = new Student("ivan", 11, "9F", 0.0);
        studentList = new ArrayList<>(Arrays.asList(student1, student2, student3));
    }

    @Test
    void saveStudentTest () {
        when(studentDao.save(student1)).thenReturn(student1);

        studentService.saveStudent(student1);

        verify(studentDao, times(1)).save(student1);
    }

    @Test
    void getStudentsByTeacherIdTest () {

        when(studentDao.findStudentsByTeachersId(1L)).thenReturn(studentList);

        Set<Student> students = studentService.getStudentsByTeacherId(1L);

        assertEquals(3, students.size());
    }

    @Test
    void getAllStudentsTest () {
        when(studentDao.findAll()).thenReturn(studentList);

        List<Student> students = studentService.getAllStudents();

        assertEquals(3, students.size());
    }

    @Test
    void getStudentByNameTest () {
        studentList.remove(0);
        when(studentDao.findByName("artur")).thenReturn(studentList);
        when(studentDao.findByName("minotavr")).thenThrow(new NullPointerException());

        Student student = studentService.getStudentByName("artur");

        assertNull(student.getSchool());
        assertThrows(NullPointerException.class, () -> studentService.getStudentByName("minotavr"));
    }

    @Test
    void getStudentByIdTest () {
        when(studentDao.findById(3L)).thenReturn(Optional.ofNullable(student3));
        when(studentDao.findById(4L)).thenThrow(new NullPointerException());

        Student student = studentService.getStudentById(3L);

        assertEquals(11, student.getSchool());
        assertThrows(NullPointerException.class, () -> studentService.getStudentById(4L));
    }

    @Test
    void updateStudentTest () {
        Student newStudent = new Student("nino", 13, "11F", 10.0);

        when(studentDao.findById(3L)).thenReturn(Optional.ofNullable(student3));
        when(studentDao.findById(4L)).thenThrow(new NullPointerException());
        when(studentDao.save(student3)).thenReturn(newStudent);

        Student updateStudent = studentService.updateStudent(3L, newStudent);

        assertNotEquals(13, student3.getSchool());
        assertEquals(13, updateStudent.getSchool());
        assertThrows(NullPointerException.class, () -> studentService.getStudentById(4L));

    }

    @Test
    void findTeacherByNameTest () {
        Teacher lion = new Teacher("lion", "1234", new Role("user"));
        List <Teacher> teacherList = new ArrayList<>();
        teacherList.add(lion);
        when(teacherDao.findByName("lion")).thenReturn(teacherList);

        List <Teacher> findTeacherList = studentService.findTeacherByName("lion");

        assertTrue(findTeacherList.contains(lion));
    }

    @Test
    void showQuestionnaireTest () {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(10L);
        when(questionnaireService.getQuestionnaireById(1L)).thenReturn(questionnaire);

        Questionnaire findQuestionnaire = studentService.showQuestionnaire(1L);

        assertEquals(10L, findQuestionnaire.getId());
    }

    @Test
    void passTestingTest () {
        Long studentId = 1L;
        Long questId = 1L;

        List<String> questions = new ArrayList<>(Arrays.asList("t", "a", "g"));
        Questionnaire questionnaireFromStudent = new Questionnaire("biology", questions);
        questionnaireFromStudent.setId(questId);

        List<String> questions1 = new ArrayList<>(Arrays.asList("t", "a", "g"));
        Questionnaire questionnaireFromDB = new Questionnaire("biology", questions1);
        questionnaireFromDB.setId(questId);

        Set<Questionnaire> notPassable = new HashSet<>();
        notPassable.add(questionnaireFromDB);
        student3.setNotPassable(notPassable);

        when(studentDao.findById(studentId)).thenReturn(Optional.ofNullable(student3));
        when(questionnaireService.getQuestionnaireById(questId)).thenReturn(questionnaireFromDB);
        when(studentDao.save(student3)).thenReturn(student3);

        studentService.passTesting(studentId, questId, questionnaireFromStudent);

        verify(studentDao, times(1)).findById(studentId);
        verify(questionnaireService, times(2)).getQuestionnaireById(questId);
        verify(studentDao, times(1)).save(student3);
        assertThrows(RuntimeException.class,
                () -> studentService.passTesting(studentId, 666L, questionnaireFromStudent));

//        List<String> questionsIncorrect = new ArrayList<>(List.of("t", "x", "y"));
//        Questionnaire inCorrect = new Questionnaire("biology", questionsIncorrect);
//
//        notPassable.add(questionnaireFromDB);
//        student3.setNotPassable(notPassable);
//
//        studentService.passTesting(studentId, questId, inCorrect);
//
//        assertThrows(RuntimeException.class,
//                () -> studentService.passTesting(studentId, questId, inCorrect));

    }

}
