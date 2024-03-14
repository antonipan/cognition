package ru.antonio.cognition.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QuestionnaireTest {

    Questionnaire questionnaire;
    List<String> questions;

    @BeforeEach
    public void setup () {
        MockitoAnnotations.openMocks(this);
        questionnaire = new Questionnaire();
        questionnaire.setId(10L);
        questions = new ArrayList<>(Arrays.asList("t", "b", "p", "x"));
        questionnaire.setQuestions(questions);
     }

    @Test
    void getIdTest () {
        assertEquals(10L, questionnaire.getId());
    }

    @Test
    void setIdTest () {
        questionnaire.setId(11L);
        assertNotEquals(10, questionnaire.getId());
        assertEquals(11L, questionnaire.getId());
    }

    @Test
    void getNameTest() {
        questionnaire.setName("bob");
        assertEquals("bob", questionnaire.getName());
    }

    @Test
    void setNameTest () {
        questionnaire.setName("gor");
        assertEquals("gor", questionnaire.getName());
    }

    @Test
    void getQuantityQuestionsTest () {
        assertEquals(questions.size(), questionnaire.getQuantityQuestions());
    }

    @Test
    void setQuantityQuestionsTest ()  {
        assertThrows(IllegalArgumentException.class, () -> questionnaire.setQuantityQuestions(100));
    }

    @Test
    void getShareCorrectAnswerTest () {
        assertEquals(2.0, questionnaire.getShareCorrectAnswers());
    }

    @Test
    void setShareCorrectAnswerTest () {
        questionnaire.setShareCorrectAnswers(10);
        assertEquals(10, questionnaire.getShareCorrectAnswers());
    }

    @Test
    void getQuestionsTest () {
        assertEquals(4, questionnaire.getQuestions().size());
    }

    @Test
    void setQuestionsTest () {
        questions.add("x");
        questionnaire.setQuestions(questions);
        assertNotEquals(4, questionnaire.getQuestions().size());
    }

    private Set <Teacher> getTeacherSetForTesting () {
        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject("history"));
        Teacher teacher1 = new Teacher("pavel", subjects, 5);
        Teacher teacher2 = new Teacher("petr", subjects, 15);
        Teacher teacher3 = new Teacher("luka", subjects, 25);
        return new HashSet<>(Arrays.asList(teacher1, teacher2, teacher3));
    }

    @Test
    void getTeachersTest () {
        questionnaire.setTeachers(getTeacherSetForTesting());
        assertEquals(3, questionnaire.getTeachers().size());
    }

    @Test
    void setTeachersTest() {
        Set <Teacher> teachers = new HashSet<>();
        questionnaire.setTeachers(teachers);
        assertEquals(0, questionnaire.getTeachers().size());
    }

    @Test
    void getNotStudentsTest () {
        Student student1 = new Student();
        Student student2 = new Student();
        Set<Student> notStudents = new HashSet<>(Arrays.asList(student1, student2));
        questionnaire.setNotStudents(notStudents);
        assertEquals(2, questionnaire.getNotStudents().size());
    }

    @Test
    void setNotStudentsTest () {
        Student student1 = new Student();
        Set<Student> notStudents = new HashSet<>();
        questionnaire.setNotStudents(notStudents);
        assertNotEquals(1, questionnaire.getNotStudents().size());
        notStudents.add(student1);
        assertEquals(1, questionnaire.getNotStudents().size());
    }

    @Test
    void getAndSetStudentsTest () {
        Student student1 = new Student();
        Set<Student> students = new HashSet<>();
        assertNotEquals(1, questionnaire.getStudents().size());
        students.add(student1);
        questionnaire.setStudents(students);
        assertEquals(1, questionnaire.getStudents().size());
    }

    @Test
    void addQuestionsTest () {
        String question1 = "i";
        assertNotEquals(5, questionnaire.getQuestions().size());
        questionnaire.addQuestion(question1);
        assertEquals(5, questionnaire.getQuestions().size());
    }

    @Test
    void deleteQuestionsTest () {
        String question1 = "i";
        assertNotEquals(3, questionnaire.getQuestions().size());
        questionnaire.deleteQuestion(3);
        assertEquals(3, questionnaire.getQuestions().size());
    }
}
