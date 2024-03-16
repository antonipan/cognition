package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Teacher;

import java.util.List;

/**
 * Интерфейс упралвения бизнес-логикой сущности {@link Teacher}
 *
 * @author Antonio
 * @version 1.0
 */
public interface TeacherService {

    List<Teacher> saveAllTeacher(List<Teacher> teachers);

    Teacher updateTeacher (Long id, Teacher teacher);
}
