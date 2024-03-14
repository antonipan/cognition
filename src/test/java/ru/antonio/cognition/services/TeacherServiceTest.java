package ru.antonio.cognition.services;

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

    List<Teacher> teacherList;

    @BeforeEach
    public void setup () {
        MockitoAnnotations.openMocks(this);

        teacherList = new ArrayList<>();
        Subject subject = new Subject("history");
        Set<Subject> subjects = new HashSet<>();
        subjects.add(subject);

        Teacher teacher1 = new Teacher("ivan", subjects, 5);
        Teacher teacher2 = new Teacher("Inna", subjects, 10);
        Teacher teacher3 = new Teacher("Pavel", subjects, 3);

        teacherList.add(teacher1);
        teacherList.add(teacher2);
        teacherList.add(teacher3);

        System.out.println(teacherList.size() + " РАЗМЕР ЛИСТА") ;

    }

    @Test
    public void saveTeacherTest () {
        Teacher teacher = new Teacher();
        teacherService.saveTeacher(teacher);
        Mockito.verify(teacherDao, Mockito.times(1)).save(teacher);
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

//    @Test
//    public void getTeacherByExperienceTest () {
//        Teacher teacher = new Teacher("p", new HashSet<>(), 5);
//        List <Teacher> teachers = new ArrayList<>();
//        teachers.add(teacher);
//        when(teacherDao.findByName(1L)).thenReturn(teachers);
//
//        List <Teacher> foundlist = teacherService.getTeacherById(1);
//        assertEquals(5, foundlist.get(0).getExperience());
//
//    }
}
