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
import ru.antonio.cognition.aspects.TrackTeacherActionAspect;
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.repositories.TeachRepository;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeacherServiceTest {

    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeachRepository teachRepository;

    List<Teacher> teacherList;

    @BeforeEach
    public void setup () {
        MockitoAnnotations.openMocks(this);

        teacherList = new ArrayList<>();

        Teacher teacher1 = new Teacher("ivan", "history", 5);
        Teacher teacher2 = new Teacher("Inna", "Math", 10);
        Teacher teacher3 = new Teacher("Pavel", "Politica", 3);

        teacherList.add(teacher1);
        teacherList.add(teacher2);
        teacherList.add(teacher3);

        System.out.println(teacherList.size() + " РАЗМЕР ЛИСТА") ;

    }

    @Test
    public void saveTeacherTest () {
        Teacher teacher = new Teacher();
        teacherService.saveTeacher(teacher);
        Mockito.verify(teachRepository, Mockito.times(1)).save(teacher);
    }

    @Test
    public void saveAllTeachers () {
        teacherService.saveAllTeacher(teacherList);

        Mockito.verify(teachRepository, Mockito.times(1)).saveAll(teacherList);
    }

    @Test
    public void getListTeachers () {
        when(teachRepository.findAll()).thenReturn(teacherList);
        List <Teacher> foundTeachers = teacherService.getListTeachers();
        assertEquals(3, foundTeachers.size());

    }

    @Test
    public void getTeacherByExperienceTest () {
        Teacher teacher = new Teacher("p", "v", 5);
        List <Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        when(teachRepository.findByExperience(5)).thenReturn(teachers);

        List <Teacher> foundlist = teacherService.getListByExperience(5);
        assertEquals(5, foundlist.get(0).getExperience());

    }
}
