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
import ru.antonio.cognition.models.Teacher;
import ru.antonio.cognition.repositories.TeachRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeacherServiceTest {

    @Mock
    private TeachRepository teachRepository;

    InjectMocks
    private TeacherService teacherService;

    Teacher teacher1;
    Teacher teacher2;
    Teacher teacher3;

    List<Teacher> teacherList;

    @BeforeEach
    public void setup () {
        MockitoAnnotations.openMocks(this);
        teacher1 = new Teacher("ivan", "history", 5);
        teacher1.setId(10L);

        teacher2 = new Teacher("Inna", "Math", 10);
        teacher2.setId(100L);

        teacher3 = new Teacher("", "Math", 10);
        teacher3.setId(1000L);

        teacherList.add(teacher1);
        teacherList.add(teacher2);
        teacherList.add(teacher3);
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




}
