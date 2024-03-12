package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Subject;

public interface SubjectDao extends JpaRepository<Subject, Integer> {
}
