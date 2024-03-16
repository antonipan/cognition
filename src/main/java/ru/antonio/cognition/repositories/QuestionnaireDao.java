package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Questionnaire;

import java.util.List;

/**
 * Класс общарения к хранилищу сущности {@link Questionnaire}
 *
 * @author Antonio
 * @version 1.0
 */
public interface QuestionnaireDao extends JpaRepository<Questionnaire, Long> {
// TODO: 16.03.2024 дописать документацию

    List<Questionnaire> findQuestionnairesByTeachersId(Long teacherId);

    List<Questionnaire> findByName(String questName);

}
