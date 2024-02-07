package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.model.Questionnaire;
import ru.antonio.cognition.repositories.QuestionnaireRepository;

import java.util.List;

@Service
public class QuestionnaireService {
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionnaireService (QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    public List <Questionnaire> saveAllQuestionnaire (List<Questionnaire> questionnaireList ) {
        return questionnaireRepository.saveAll(questionnaireList);
    }

    public List <Questionnaire> getAllQuestionnaire () {
        return questionnaireRepository.findAll();
    }

    public Questionnaire getQuestionnaireById(Long id) {
        return questionnaireRepository.findById(id).orElse(null);
    }
}
