package ru.antonio.cognition.questionnaires;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    private QuestionnaireRepository questionnaireRepository;

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

        Mockito.verify(questionnaireRepository, Mockito.times(2)).saveAll(list);
    }

    @Test
    public void getAllQuestionnaireTest () {
        when(questionnaireRepository.findAll()).thenReturn(list);
        List <Questionnaire> getList = questionnaireService.getAllQuestionnaire();
        assertEquals(2, getList.size());
    }

    @Test
    public void getQuestionnaireById () {
        Long id = 333L;
        Long anotherId = 444L;
        Questionnaire questionnaire = list.get(1);
        questionnaire.setId(id);

        when(questionnaireRepository.findById(id)).thenReturn(Optional.of(questionnaire));

        Questionnaire foundQuestionnaire = questionnaireService.getQuestionnaireById(id);
        assertEquals(id, foundQuestionnaire.getId());
        assertNotEquals(anotherId, foundQuestionnaire.getId());
    }
}
