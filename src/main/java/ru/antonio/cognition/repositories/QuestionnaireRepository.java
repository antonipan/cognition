package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
}
