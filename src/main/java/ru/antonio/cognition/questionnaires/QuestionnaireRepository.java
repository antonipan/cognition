package ru.antonio.cognition.questionnaires;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.questionnaires.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
}
