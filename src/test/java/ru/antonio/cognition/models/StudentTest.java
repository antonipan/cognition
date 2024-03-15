package ru.antonio.cognition.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StudentTest {

    private Student student;
    private Set<Subject> subjects;
    private Set <Teacher> teachers;
    private Set <Questionnaire> notPassable;
    private Set <Questionnaire> passable;

    @BeforeEach
    void setup () {
        student = new Student("tom", 1, "9A", 3.0);
        Student student1 = new Student("tom", 1, "9A", 3.0);
        Student student2 = new Student("tom", 1, "9A", 3.0);
        Student student3 = new Student("tom", 1, "9A", 3.0);
        subjects = new HashSet<>();
        notPassable = new HashSet<>();
        passable = new HashSet<>();
        subjects.add(new Subject("math"));
        Questionnaire questionnaire1 = new Questionnaire("ram", new ArrayList<>(Arrays.asList("t", "p", "v")));
        Questionnaire questionnaire2 = new Questionnaire("bam", new ArrayList<>(Arrays.asList("t", "p", "v")));
        notPassable.add(questionnaire1);
        passable.add(questionnaire2);
        teachers = new HashSet<>();
    }

    @Test
    void getIdAndSetI () {
        assertNull(student.getId());
        student.setId(333L);
        assertEquals(333L, student.getId());
    }

    @Test
    void getNameAndSetName () {
        assertEquals("tom", student.getName());
        student.setName("eva");
        assertNotEquals("tom", student.getName());
    }

    @Test
    void getSchoolAndSetSchoolTest () {
        assertEquals(1, student.getSchool());
        student.setSchool(20);
        assertNotEquals(1, student.getSchool());
    }

    @Test
    void getGradeTest () {
        assertEquals("9A", student.getGrade());
    }

    @Test
    void setGradeTest () {
        student.setGrade("10A");
        assertEquals("10A", student.getGrade());
    }

    @Test
    void getProgressTest () {
        assertEquals(3.0, student.getProgress());
    }

    @Test
    void setProgressTest () {
        student.setProgress(5.0);
        assertEquals(8.0, student.getProgress());
    }

    @Test
    void getSubjectsTest () {
        assertEquals(0, student.getSubjects().size());
    }

    @Test
    void setSubjectsTest () {
        student.setSubjects(subjects);
        assertEquals(1, student.getSubjects().size());
    }

    @Test
    void getTeachersTest () {
        assertEquals(0, student.getTeachers().size());
    }

    @Test
    void setTeachersTest () {
        teachers.add(new Teacher());
        student.setTeachers(teachers);
        assertEquals(1, student.getTeachers().size());
    }

    @Test
    void getNotPassableTest () {
        assertEquals(0, student.getNotPassable().size());
    }

    @Test
    void setNotPassableTest () {
        student.setNotPassable(notPassable);
        assertEquals(1, student.getNotPassable().size());
    }

    @Test
    void getPassableTest () {
        assertEquals(0, student.getPassable().size());
    }

    @Test
    void setPassableTest () {
        student.setPassable(passable);
        assertEquals(1, student.getPassable().size());
    }

    @Test
    void addNotPassTest () {
        Questionnaire questionnaire = new Questionnaire("bam", new ArrayList<>(Arrays.asList("t", "p", "v")));
        assertEquals(0, student.getNotPassable().size());
        assertEquals(0, student.getPassable().size());
        student.addNotPass(questionnaire);
        assertEquals(1, student.getNotPassable().size());
        assertEquals(0, student.getPassable().size());
    }

    @Test
    void deleteNotPassAndAddPassTest () {
        Questionnaire questionnaire = new Questionnaire("bam", new ArrayList<>(Arrays.asList("t", "p", "v")));
        assertEquals(0, student.getNotPassable().size());
        assertEquals(0, student.getPassable().size());
        student.addNotPass(questionnaire);
        assertEquals(1, student.getNotPassable().size());
        assertEquals(0, student.getPassable().size());
        student.deleteNotPassAndAddPass(questionnaire);
        assertEquals(0, student.getNotPassable().size());
        assertEquals(1, student.getPassable().size());
    }

}
