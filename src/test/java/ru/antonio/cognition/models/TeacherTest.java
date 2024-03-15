package ru.antonio.cognition.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeacherTest {

    private Teacher teacher;
    private Set<Subject> subjectSet;
    private Set<Questionnaire> questionnaireSet;
    private Set<Student> studentSet;
    private Role role;

    @BeforeEach
    public void setup () {
        role = new Role("teacher");

        subjectSet = new HashSet<>();
        subjectSet.add(new Subject("history"));

        studentSet = new HashSet<>();
        studentSet.add(new Student());

        teacher = new Teacher("vold", subjectSet, 3);

        questionnaireSet = new HashSet<>();
        questionnaireSet.add(new Questionnaire());
    }

    @Test
    void getIdTest () {
        assertNull(teacher.getId());
    }

    @Test
    void setIdTest () {
        teacher.setId(1L);
        assertEquals(1, teacher.getId());
    }

    @Test
    void getNameTest () {
        assertEquals("vold", teacher.getName());
    }

    @Test
    void setNameTest () {
        teacher.setName("old");
        assertEquals("old", teacher.getName());
    }

    @Test
    void getExperienceTest () {
        assertEquals(3, teacher.getExperience());
    }

    @Test
    void setExperienceTest () {
        teacher.setExperience(5);
        assertEquals(5, teacher.getExperience());
    }

    @Test
    void getSubjectsTest () {
        assertEquals(1, teacher.getSubjects().size());
    }

    @Test
    void setSubjectsTest () {
        Subject logic = new Subject("logic");
        subjectSet.add(logic);
        teacher.setSubjects(subjectSet);
        assertEquals(2, teacher.getSubjects().size());
    }

    @Test
    void addSubjectToSubjects () {
        Subject logic = new Subject("logic");
        teacher.addSubjectToSubjects(logic);
        assertTrue(teacher.getSubjects().contains(logic));
    }

    @Test
    void deleteSubjectTest () {
        Subject logic = new Subject("logic");
        teacher.addSubjectToSubjects(logic);
        teacher.deleteSubject(logic);
        assertFalse(teacher.getSubjects().contains(logic));
    }

    @Test
    void getQuestionnairesTest () {
        assertEquals(0, teacher.getQuestionnaires().size());
    }

    @Test
    void setQuestionnairesTest () {
        questionnaireSet.add(new Questionnaire());
        teacher.setQuestionnaires(questionnaireSet);
        assertEquals(2, teacher.getQuestionnaires().size());
    }

    @Test
    void addQuestToAllQuestionnairesTest () {
        Questionnaire logic = new Questionnaire();
        teacher.addQuestToAllQuestionnaires(logic);
        assertTrue(teacher.getQuestionnaires().contains(logic));
    }

    @Test
    void deleteQuestFromMyQuestsTest () {
        Questionnaire logic = new Questionnaire();
        teacher.addQuestToAllQuestionnaires(logic);
        teacher.deleteQuestFromMyQuests(logic);
        assertFalse(teacher.getQuestionnaires().contains(logic));
    }

    @Test
    void getStudentsTest () {
        assertEquals(0, teacher.getStudents().size());
    }

    @Test
    void setStudentsTest () {
        Student student = new Student();
        studentSet.add(student);
        teacher.setStudents(studentSet);
        assertEquals(2, teacher.getStudents().size());
    }

    @Test
    void addStudentToTeacherTest () {
        Student student = new Student();
        teacher.addStudentToTeacher(student);
        assertTrue(teacher.getStudents().contains(student));
    }

    @Test
    void deleteStudentFromTeacherTest () {
        Student student = new Student();
        teacher.addStudentToTeacher(student);
        teacher.deleteStudentFromTeacher(student);
        assertFalse(teacher.getStudents().contains(student));
    }
}
