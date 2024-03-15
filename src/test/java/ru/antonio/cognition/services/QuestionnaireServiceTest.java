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
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.repositories.QuestionnaireDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QuestionnaireServiceTest {

    @InjectMocks
    private QuestionnaireService questionnaireService;

    @Mock
    private QuestionnaireDao questionnaireDao;

    List<Questionnaire> list;

    @BeforeEach
    public void setup () {
        MockitoAnnotations.openMocks(this);
        Questionnaire qsn1 = new Questionnaire();
        Questionnaire qsn2 = new Questionnaire();
        list = new ArrayList<>();
        list.add(qsn1);
        list.add(qsn2);
    }

    @Test
    public void saveAllQuestionnaire() {

        questionnaireService.saveAllQuestionnaire(list);
        questionnaireService.saveAllQuestionnaire(list);

        Mockito.verify(questionnaireDao, Mockito.times(2)).saveAll(list);
    }

    @Test
    public void getAllQuestionnaireTest () {
        when(questionnaireDao.findAll()).thenReturn(list);
        List <Questionnaire> getList = questionnaireService.getAllQuestionnaire();
        assertEquals(2, getList.size());
    }

    @Test
    public void getQuestionnaireById () {
        Long id = 333L;
        Long anotherId = 444L;
        Questionnaire questionnaire = list.get(1);
        questionnaire.setId(id);

        when(questionnaireDao.findById(id)).thenReturn(Optional.of(questionnaire));

        Questionnaire foundQuestionnaire = questionnaireService.getQuestionnaireById(id);
        assertEquals(id, foundQuestionnaire.getId());
        assertNotEquals(anotherId, foundQuestionnaire.getId());
    }

    @Test
    void getQuestionnairesByTeacherIdTest () {
        Long teacherId = 33L;

        when(questionnaireDao.findQuestionnairesByTeachersId(teacherId))
                .thenReturn(list);
        List<Questionnaire> listToRepository = questionnaireService.getQuestionnairesByTeacherId(teacherId);

        assertEquals(2, listToRepository.size());
    }
}
