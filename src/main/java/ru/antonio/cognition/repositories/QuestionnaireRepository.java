package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.model.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
}
