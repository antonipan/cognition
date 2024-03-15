package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.repositories.QuestionnaireDao;

import java.util.List;

@Service
public class QuestionnaireService {
    private QuestionnaireDao questionnaireDao;

    @Autowired
    public QuestionnaireService (QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    public List <Questionnaire> saveAllQuestionnaire (List<Questionnaire> questionnaireList ) {
        return questionnaireDao.saveAll(questionnaireList);
    }

    public Questionnaire saveOneQuestionnaire (Questionnaire questionnaire) {
        return questionnaireDao.save(questionnaire);
    }

    public List <Questionnaire> getAllQuestionnaire () {
        return questionnaireDao.findAll();
    }

    public Questionnaire getQuestionnaireById(Long id) {
        return questionnaireDao.findById(id).orElse(null);
    }

    public List<Questionnaire> getQuestionnairesByTeacherId(Long teacherId) {
        return questionnaireDao.findQuestionnairesByTeachersId(teacherId);
    }


}
