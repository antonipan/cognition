package ru.antonio.cognition.services;

import jakarta.persistence.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.repositories.TeacherDao;
import ru.antonio.cognition.services.TeacherService;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeacherServiceTest {

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @Mock
    private TeacherDao teacherDao;

    @Mock
    private SubjectService subjectService;

    @Mock
    private QuestionnaireService questService;

    @Mock
    private StudentServiceImpl studentService;


    List<Teacher> teacherList;

    @BeforeEach
    public void setup () {
        MockitoAnnotations.openMocks(this);

        teacherList = new ArrayList<>();
        Subject subject = new Subject("history");
        Set<Subject> subjects = new HashSet<>();
        subjects.add(subject);

        Teacher ivan = new Teacher("ivan", subjects, 5);
        Teacher inna = new Teacher("Inna", subjects, 10);
        Teacher pavel = new Teacher("Pavel", subjects, 3);

        teacherList.add(ivan);
        teacherList.add(inna);
        teacherList.add(pavel);

    }

    @Test
    public void saveTeacherTest () {
        Teacher teacher = new Teacher();
        teacherService.saveTeacher(teacher);
        Mockito.verify(teacherDao, Mockito.times(1)).save(teacher);
    }

    @Test
    void updateTeacherByTeacherTest () {

    }

    @Test
    void updateTeacherBySubjectTest() {

    }

    @Test
    void updateTeacherByQuestionnaireTest () {

    }


    @Test
    public void saveAllTeachers () {
        teacherService.saveAllTeacher(teacherList);

        Mockito.verify(teacherDao, Mockito.times(1)).saveAll(teacherList);
    }

    @Test
    public void getListTeachers () {
        when(teacherDao.findAll()).thenReturn(teacherList);
        List <Teacher> foundTeachers = teacherService.getListTeachers();
        assertEquals(3, foundTeachers.size());

    }

    @Test
    void getTeacherByIdTest() {

    }

    @Test
    void deleteTeacherByIdTest () {

    }

    @Test
    void getMySubjectsTest () {

    }

    @Test
    void deleteSubjectFromTeacherListTest () {

    }

    @Test
    void getAllSubjectsTest () {

    }

    @Test
    void createSubjectTest () {

    }

    @Test
    void getSubjectByIdTest () {

    }

    @Test
    void createQuestionnaire () {

    }

    @Test
    void getMyQuestionnairesTest () {

    }

    @Test
    void getQuestionnaireByIdTest () {

    }

    @Test
    void updateMyQuestionnaireTest () {

    }

    @Test
    void getAllQuestionnaireTest () {

    }

    @Test
    void findQuestByNameTest () {

    }

    @Test
    void getMyStudentsTest () {

    }

    @Test
    void getAllStudentsTest () {

    }

    @Test
    void addStudentToListTest () {

    }

    @Test
    void deleteStudentFromListTeacherTest () {

    }

    @Test
    void setQuestForStudentTest () {

    }

    @Test
    void getTeacherByNameTest () {

    }
}
