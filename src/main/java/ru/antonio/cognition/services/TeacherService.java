package ru.antonio.cognition.services;

import ru.antonio.cognition.models.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> saveAllTeacher(List<Teacher> teachers);

    Teacher updateTeacher (Long id, Teacher teacher);
}
