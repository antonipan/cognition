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

import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Student;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.repositories.TeacherDao;
import ru.antonio.cognition.services.TeacherService;


import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.isA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    private Teacher ivan;
    private Teacher inna;
    private Teacher pavel;
    private List<Teacher> teacherList;

    private Subject logic;
    private Set <Subject> subjects;

    @BeforeEach
    public void setup () {
        MockitoAnnotations.openMocks(this);

        teacherList = new ArrayList<>();
        logic = new Subject("logic");
        subjects = new HashSet<>();
        subjects.add(logic);

        ivan = new Teacher("ivan", subjects, 5);
        inna = new Teacher("inna", subjects, 10);
        pavel = new Teacher("pavel", subjects, 3);

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
        Long teachId = 1L;
        when(teacherDao.findById(teachId)).thenReturn(Optional.ofNullable(ivan));
        when(teacherDao.findById(33L)).thenThrow(new NullPointerException());

        Teacher updateIvan = teacherService.updateTeacher(teachId, inna);

        assertEquals("inna", updateIvan.getName());
        assertThrows(NullPointerException.class,
                () -> teacherService.updateTeacher(33L, inna));

    }

    @Test
    void updateTeacherBySubjectTest() {
        Long teachId = 1L;
        when(teacherDao.findById(teachId)).thenReturn(Optional.ofNullable(ivan));
        when(teacherDao.findById(33L)).thenThrow(new NullPointerException());

        Teacher updateIvan = teacherService.updateTeacher(teachId, new Subject("music"));

        assertNotNull(updateIvan.getSubjects().stream().filter(s -> s.getName().equals("music")).findFirst());
        assertThrows(NullPointerException.class,
                () -> teacherService.updateTeacher(33L, new Subject("music")));
    }

    @Test
    void updateTeacherByQuestionnaireTest () {
        Long teachId = 1L;
        when(teacherDao.findById(teachId)).thenReturn(Optional.ofNullable(ivan));
        when(teacherDao.findById(33L)).thenThrow(new NullPointerException());

        List<String> questions = new ArrayList<>(Arrays.asList("g", "o", "g", "i", "a"));
        Questionnaire questionnaire = new Questionnaire("math", questions);
        List <Questionnaire> questionnaires = new ArrayList<>();
        questionnaires.add(questionnaire);

        when(teacherDao.save(ivan)).thenReturn(ivan);

        when(questService.getQuestionnairesByTeacherId(teachId)).thenReturn(questionnaires);

        List<Questionnaire> findListQuest = teacherService.updateTeacher(teachId, questionnaire);

        assertEquals(1, findListQuest.size());
        assertThrows(NullPointerException.class,
                () -> teacherService.updateTeacher(33L, questionnaire));
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
        Long teacherId = 1L;
        when(teacherDao.findById(teacherId)).thenReturn(Optional.ofNullable(inna));
        when(teacherDao.findById(2L)).thenThrow(new NullPointerException());

        Teacher teacher = teacherService.getTeacherById(teacherId);

        assertEquals("inna", teacher.getName());
        assertThrows(NoSuchElementException.class,
                () -> teacherService.getTeacherById(33L));
    }

    @Test
    void deleteTeacherByIdTest () {
        Long teacherId = 1L;

        doNothing().when(teacherDao).deleteById(teacherId);

        teacherService.deleteTeacherById(teacherId);

        verify(teacherDao, times(1)).deleteById(teacherId);
    }

    @Test
    void getMySubjectsTest () {
        Long teacherId = 1L;
        when(subjectService.getSubjectsByTeacherId(teacherId))
                .thenReturn(new ArrayList<>(subjects));

        List<Subject> findSubjects = teacherService.getMySubjects(teacherId);

        assertEquals(1, findSubjects.size());
    }

    @Test
    void deleteSubjectFromTeacherListTest () {
        Long teachId = 1L;
        Integer subjId = 11;

        when(teacherDao.findById(teachId)).thenReturn(Optional.ofNullable(inna));
        when(teacherDao.findById(2L)).thenThrow(new NullPointerException());

        when(subjectService.getSubjectById(subjId)).thenReturn(logic);
        when(teacherDao.save(inna)).thenReturn(inna);
        inna.addSubjectToSubjects(logic);

        assertEquals(1, inna.getSubjects().size());
        teacherService.deleteSubjectFromTeacherList(teachId, subjId);

        assertEquals(0, inna.getSubjects().size());
        assertThrows(NullPointerException.class,
                () -> teacherService.deleteSubjectFromTeacherList(2L, subjId));

    }

    @Test
    void getAllSubjectsTest () {
        when(subjectService.getAllSubject()).thenReturn(subjects);

        Set<Subject> findSubject = teacherService.getAllSubjects();

        assertTrue(findSubject.contains(logic));
    }

    @Test
    void createSubjectTest () {
        when(subjectService.createSubject(logic)).thenReturn(logic);

        Subject findSubject = teacherService.createSubject(logic);

        assertEquals("logic", findSubject.getName());
    }

    @Test
    void getSubjectByIdTest () {
        Integer subId = 1;

        when(subjectService.getSubjectById(subId)).thenReturn(logic);

        Subject findSubject = teacherService.getSubjectById(subId);

        assertEquals("logic", findSubject.getName());
    }

    @Test
    void createQuestionnaire () {
        List<String> questions = new ArrayList<>(Arrays.asList("g", "o", "g", "i", "a"));
        Questionnaire questionnaire = new Questionnaire("math", questions);

        when(questService.saveOneQuestionnaire(questionnaire)).thenReturn(questionnaire);

        Questionnaire findQuest = teacherService.createQuestionnaire(questionnaire);

        assertEquals("math", findQuest.getName());
    }

    @Test
    void getMyQuestionnairesTest () {
        Long teacherId = 1L;
        List<String> questions = new ArrayList<>(Arrays.asList("g", "o", "g", "i", "a"));
        Questionnaire questionnaire = new Questionnaire("math", questions);
        List <Questionnaire> questionnaires = new ArrayList<>();
        questionnaires.add(questionnaire);

        when(questService.getQuestionnairesByTeacherId(teacherId)).thenReturn(questionnaires);

        List<Questionnaire> findQuestionnaire = teacherService.getMyQuestionnaires(teacherId);

        assertEquals(1, findQuestionnaire.size());
    }

    @Test
    void getQuestionnaireByIdTest () {
        Long questId = 1L;
        List<String> questions = new ArrayList<>(Arrays.asList("g", "o", "g", "i", "a"));
        Questionnaire questionnaire = new Questionnaire("math", questions);

        when(questService.getQuestionnaireById(questId)).thenReturn(questionnaire);

        Questionnaire findQuestion = teacherService.getQuestionnaireById(questId);

        assertEquals("math", findQuestion.getName());
    }

    @Test
    void updateMyQuestionnaireTest () {
        Long questId = 1L;
        List<String> questions = new ArrayList<>(Arrays.asList("g", "o", "g", "i", "a"));
        Questionnaire questionnaire = new Questionnaire("math", questions);

        when(questService.saveOneQuestionnaire(questionnaire)).thenReturn(questionnaire);
        when(questService.getQuestionnaireById(questId)).thenReturn(questionnaire);

        Questionnaire findQuest = teacherService.updateMyQuestionnaire(questId, questionnaire);

        assertEquals("math", findQuest.getName());

    }

    @Test
    void deleteQuestFromMyListTest () {
        Long teachId = 1L;

        when(teacherDao.findById(teachId)).thenReturn(Optional.ofNullable(inna));
        when(teacherDao.findById(2L)).thenThrow(new NullPointerException());

        Long questId = 11L;
        List<String> questions = new ArrayList<>(Arrays.asList("g", "o", "g", "i", "a"));
        Questionnaire questionnaire = new Questionnaire("math", questions);

        Set <Questionnaire> questionnaires = new HashSet<>();
        questionnaires.add(questionnaire);
        inna.setQuestionnaires(questionnaires);

        when(teacherDao.save(inna)).thenReturn(inna);

        when(questService.getQuestionnaireById(questId)).thenReturn(questionnaire);
        List<Questionnaire> questionnaireList = new ArrayList<>(questionnaires);
        when(questService.getQuestionnairesByTeacherId(teachId)).thenReturn(questionnaireList);

        List <Questionnaire> find = teacherService.deleteQuestFromMyList(teachId, questId);

        assertEquals(1, find.size());
        assertThrows(NullPointerException.class,
                () -> teacherService.deleteQuestFromMyList(2L, questId));


    }

    @Test
    void getAllQuestionnaireTest () {
        Questionnaire questionnaire = new Questionnaire();
        List<Questionnaire> questList = new ArrayList<>();
        questList.add(questionnaire);
        when(questService.getAllQuestionnaire()).thenReturn(questList);

        List<Questionnaire> findQuest = teacherService.getAllQuestionnaire();

        assertEquals(1, findQuest.size());
    }

    @Test
    void findQuestByNameTest () {
        Questionnaire questionnaire = new Questionnaire("math", new ArrayList<>(Arrays.asList("t", "a", "k")));
        List<Questionnaire> questList = new ArrayList<>();
        questList.add(questionnaire);
        when(questService.getAllQuestionnaire()).thenReturn(questList);

        Set<Questionnaire> findQuest = teacherService.findQuestByName("math");

        assertEquals(1, findQuest.size());
    }

    @Test
    void getMyStudentsTest () {
        Student igor = new Student();
        Student pavel = new Student();
        Long teachId = 1L;
        Set<Student> students = new HashSet<>(Arrays.asList(igor, pavel));
        when(studentService.getStudentsByTeacherId(teachId)).thenReturn(students);

        Set<Student> findStud = teacherService.getMyStudents(teachId);

        assertEquals(2, findStud.size());
    }

    @Test
    void getAllStudentsTest () {
        Student igor = new Student();
        Student pavel = new Student();
        List <Student> students = new ArrayList<>(Arrays.asList(igor, pavel));
        when(studentService.getAllStudents()).thenReturn(students);

        List <Student> findStudent = teacherService.getAllStudents();

        assertEquals(2, findStudent.size());
    }

    @Test
    void addStudentToListTest () {
        Long teachId = 1L;
        when(teacherDao.findById(teachId)).thenReturn(Optional.ofNullable(inna));
        when(teacherDao.findById(2L)).thenThrow(new NullPointerException());

        Long studId = 5L;
        Student vals = new Student();
        when(studentService.getStudentByName("vals")).thenReturn(vals);

        when(teacherDao.save(inna)).thenReturn(inna);

        Student igor = new Student();
        Student pavel = new Student();
        Set<Student> students = new HashSet<>(List.of(vals));
        when(studentService.getStudentsByTeacherId(teachId)).thenReturn(students);

        Set<Student> findStudent = teacherService.addStudentToList(teachId, "vals");

        assertEquals(1, findStudent.size());
    }

    @Test
    void deleteStudentFromListTeacherTest () {
        Long teachId = 1L;
        when(teacherDao.findById(teachId)).thenReturn(Optional.ofNullable(inna));
        when(teacherDao.findById(2L)).thenThrow(new NullPointerException());

        Long studId = 11L;
        Student bob = new Student();

        when(teacherDao.save(inna)).thenReturn(inna);

        List<Questionnaire> questionnaireList = new ArrayList<>();
        when(questService.getQuestionnairesByTeacherId(teachId))
                .thenReturn(new ArrayList<>(inna.getQuestionnaires()));

        List<Questionnaire> findQuest = teacherService.deleteStudentFromListTeacher(teachId, st);
    }

    @Test
    void setQuestForStudentTest () {

    }

    @Test
    void getTeacherByNameTest () {

    }
}
