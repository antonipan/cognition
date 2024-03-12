package ru.antonio.cognition.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.antonio.cognition.models.Subject;

import java.util.List;

public interface SubjectDao extends JpaRepository<Subject, Integer> {

//    @Query("SELECT s FROM Subject s JOIN s.teachers WHERE s.id = (:id)")
//    List <Subject> getSubjectByTeacher (@Param("id") Long id);

    List <Subject> findSubjectsByTeachersId(Long id);

}

