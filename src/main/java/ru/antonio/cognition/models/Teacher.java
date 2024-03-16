package ru.antonio.cognition.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс сущности "Учитель" {@link Teacher}
 *
 * @author Antonio
 * @version 1.0
 */
@Entity
@Table(name = "teachers")
public class Teacher extends User {
// TODO: 16.03.2024 дописать документацию

    private String name;
    private Integer experience;

    @ManyToMany
    @JoinTable(
            name = "teach_of_sub",
                joinColumns = @JoinColumn(name = "teach_id"),
                inverseJoinColumns = @JoinColumn(name = "sub_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "teach_of_stud",
                joinColumns = @JoinColumn(name = "teach_id"),
                inverseJoinColumns = @JoinColumn(name = "stud_id")
    )
    private Set<Student> students = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "quest_of_teachers",
            joinColumns = @JoinColumn(name = "teach_id"),
            inverseJoinColumns = @JoinColumn(name = "quest_id")
    )
    private Set<Questionnaire> questionnaires = new HashSet<>();

    public Teacher() {
    }

    /**
     *
     * @param name
     * @param subjects
     * @param experience
     */
    public Teacher(String name, Set <Subject> subjects, Integer experience) {
        this.name = name;
        this.subjects = subjects;
        this.experience = experience;
    }

    public Teacher(String username, String password, Role role) {
        super(username, password, role);
        this.name = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    // WORK WITH TO COLLECTION OF SUBJECTS

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubjectToSubjects (Subject subject) {
        this.subjects.add(subject);
        subject.getTeachers().add(this);
    }

    public void deleteSubject (Subject subject) {
        this.subjects.remove(subject);
        subject.getTeachers().remove(this);
    }

    // WORK WITH TO COLLECTION OF QUESTIONNAIRES

    public Set<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Set<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public void addQuestToAllQuestionnaires (Questionnaire questionnaire) {
        this.questionnaires.add(questionnaire);
        questionnaire.getTeachers().add(this);
    }

    public void deleteQuestFromMyQuests (Questionnaire questionnaire) {
        this.questionnaires.remove(questionnaire);
        questionnaire.getTeachers().remove(this);
    }



    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudentToTeacher (Student student) {
        this.students.add(student);
        student.getTeachers().add(this);
    }

    public void deleteStudentFromTeacher (Student student) {
        this.students.remove(student);
        student.getTeachers().remove(this);
    }
}
