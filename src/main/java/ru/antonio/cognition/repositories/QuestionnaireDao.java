package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Questionnaire;

import java.util.List;

public interface QuestionnaireDao extends JpaRepository<Questionnaire, Long> {

    List<Questionnaire> findAllQuestionnairesByTeacherId(Long id);
}
