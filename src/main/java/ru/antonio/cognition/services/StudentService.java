package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Questionnaire;
import ru.antonio.cognition.models.Student;

import java.sql.Struct;
/**
 * Интерфейс упралвения бизнес-логикой сущности {@link Student}
 *
 * @author Antonio
 * @version 1.0
 */
public interface StudentService {

    void saveStudent (Student student);
}
