package ru.antonio.cognition.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SubjectTest {

    private Subject subject;
    private Set<Teacher> teacherSet;
    private Set<Student> studentSet;

    @BeforeEach
    public void setup () {
        subject = new Subject("history");
        teacherSet = new HashSet<>();
        teacherSet.add(new Teacher());
        studentSet = new HashSet<>();
        studentSet.add(new Student());
    }

    @Test
    void getIdTest () {
        assertNull(subject.getId());
    }

    @Test
    void setIdTest () {
        subject.setId(1);
        assertEquals(1, subject.getId());
    }

    @Test
    void getNameTest () {
        assertEquals("history", subject.getName());
    }

    @Test
    void setNameTest () {
        subject.setName("Russia");
        assertEquals("Russia", subject.getName());
    }

    @Test
    void getTeachersTest () {
        assertEquals(0, subject.getTeachers().size());
    }

    @Test
    void setTeachersTest () {
        subject.setTeachers(teacherSet);
        assertEquals(1, subject.getTeachers().size());
    }

    @Test
    void getStudentsTest () {
        assertEquals(0, subject.getStudents().size());

    }

    @Test
    void setStudentsTest () {
        subject.setStudents(studentSet);
        assertEquals(1, subject.getStudents().size());

    }

    @Test
    void toStringTest () {
        assertNotNull(subject.toString());
    }

}
