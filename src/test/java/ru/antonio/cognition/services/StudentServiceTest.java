package ru.antonio.cognition.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.antonio.cognition.repositories.StudentDao;
import ru.antonio.cognition.repositories.TeacherDao;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

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



}
