package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonio.cognition.models.Teacher;

import java.util.List;

public interface TeachRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByExperience(int experience);

    List<Teacher> findBySubject(String subject);

    List<Teacher> findByOrderByName();

    List<Teacher> findByOrderByExperience();

    List<Teacher> findByOrderBySubject();
}
