package ru.antonio.cognition.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.antonio.cognition.models.Subject;
import ru.antonio.cognition.repositories.SubjectDao;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SubjectServiceTest {

    @Mock
    private SubjectDao subjectDao;

    @InjectMocks
    private SubjectService subjectService;

    private Subject logic;
    private Subject philosofya;
    private Set<Subject> subjectSet;
    private List<Subject> subjectList;

    @BeforeEach
    void setup () {
        logic = new Subject("logic");
        philosofya = new Subject("philosofya");
        subjectSet = new HashSet<>();
        subjectSet.add(logic);
        subjectList = new ArrayList<>();
        subjectList.add(philosofya);
    }

    @Test
    void createSubjectTest () {
        when(subjectDao.save(logic)).thenReturn(logic);

        Subject findSubject = subjectService.createSubject(logic);

        assertEquals("logic", findSubject.getName());
    }


    @Test
    void deleteSubjectTest () {
        Integer subId = 333;

        subjectService.deleteSubject(subId);

        verify(subjectDao, times(1)).deleteById(subId);
    }

    @Test
    void getAllSubjectTest () {
        when(subjectDao.findAll()).thenReturn(subjectList);

        Set <Subject> findSubjectList = subjectService.getAllSubject();

        assertEquals(1, findSubjectList.size());
    }

    @Test
    void getSubjectByIdTest () {
        Integer subId = 1;
        Integer noSubId = 2;

        when(subjectDao.findById(subId)).thenReturn(Optional.ofNullable(logic));
        when(subjectDao.findById(noSubId)).thenThrow(new NullPointerException());

        Subject findSubjectById = subjectService.getSubjectById(subId);

        assertEquals("logic", findSubjectById.getName());
        assertThrows(NullPointerException.class, () -> subjectService.getSubjectById(noSubId));
    }

    @Test
    void getSubjectsByTeacherIdTest () {
        Long teacherId = 33L;
        Long notTeachId = 11L;
        when(subjectDao.findSubjectsByTeachersId(teacherId)).thenReturn(subjectList);
        when(subjectDao.findSubjectsByTeachersId(notTeachId)).thenThrow(new NullPointerException());

        List <Subject> findSubjects = subjectService.getSubjectsByTeacherId(teacherId);

        assertEquals(1, findSubjects.size());
        assertThrows(NullPointerException.class,
                () -> subjectService.getSubjectsByTeacherId(notTeachId));
    }
}
