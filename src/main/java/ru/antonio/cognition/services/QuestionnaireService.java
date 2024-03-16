package ru.antonio.cognition.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.repositories.QuestionnaireDao;

import java.util.List;

/**
 * Класс упралвения бизнес-логикой сущности {@link Questionnaire}
 *
 * @author Antonio
 * @version 1.0
 */
@Service
public class QuestionnaireService {
    /**
     * Хранилище сущности {@link Questionnaire}
     */
    private QuestionnaireDao questionnaireDao;

    /**
     *
     * @param questionnaireDao - хранилище методик
     */
    @Autowired
    public QuestionnaireService (QuestionnaireDao questionnaireDao) {
        this.questionnaireDao = questionnaireDao;
    }

    /**
     * сохраняет список методик
     * @param questionnaireList - список методик, которые нужно сохранить.
     * @return - список новых сохранённых методик
     */
    public List <Questionnaire> saveAllQuestionnaire (List<Questionnaire> questionnaireList ) {
        return questionnaireDao.saveAll(questionnaireList);
    }

    /**
     * сохраняет одну методику
     * @param questionnaire - методика, которую требуется сохранить
     * @return - сохранённая методика
     */
    public Questionnaire saveOneQuestionnaire (Questionnaire questionnaire) {
        return questionnaireDao.save(questionnaire);
    }

    /**
     * Получает все методики из базы данных
     * @return - список полученных методик
     */
    public List <Questionnaire> getAllQuestionnaire () {
        return questionnaireDao.findAll();
    }

    /**
     * ищет методику по её идентификатору
     * @param id - id методики
     * @return - найденная методика, или нулевой объект
     */
    public Questionnaire getQuestionnaireById(Long id) {
        return questionnaireDao.findById(id).orElse(null);
    }

    /**
     * получает список методик учителя по его ID
     * @param teacherId - ID учпителя
     * @return - список найденных методик
     */
    public List<Questionnaire> getQuestionnairesByTeacherId(Long teacherId) {
        return questionnaireDao.findQuestionnairesByTeachersId(teacherId);
    }


}
