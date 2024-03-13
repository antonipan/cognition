package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Questionnaire;

public interface QuestionnaireDao extends JpaRepository<Questionnaire, Long> {
}
