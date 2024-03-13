package ru.antonio.cognition.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "teachers")
public class Teacher extends User {

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

    @OneToMany(mappedBy = "author")
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

    public void addQuestionnaires (Questionnaire questionnaire) {
        this.questionnaires.add(questionnaire);
    }


    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
