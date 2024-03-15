package ru.antonio.cognition.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends User {

    private String name;
    private Integer school;
    private String grade;
    private Double progress;

    @ManyToMany(mappedBy = "students")
    private Set<Subject> subjects = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "stud_not_pass",
            joinColumns = @JoinColumn(name = "stud_id"),
            inverseJoinColumns = @JoinColumn(name = "quest_id")
    )
    private Set<Questionnaire> notPassable = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "stud_pass",
            joinColumns = @JoinColumn(name = "stud_id"),
            inverseJoinColumns = @JoinColumn(name = "quest_id")
    )
    private Set<Questionnaire> passable = new HashSet<>();

    public Student() {
    }

    public Student(String username, String password, Role role) {
        super(username, password, role);
        this.name = username;
    }

    public Student(String name, Integer school, String grade, Double progress) {
        this.name = name;
        this.school = school;
        this.grade = grade;
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress += progress;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Questionnaire> getNotPassable() {
        return notPassable;
    }

    public void setNotPassable(Set<Questionnaire> notPassable) {
        this.notPassable = notPassable;
    }

    public Set<Questionnaire> getPassable() {
        return passable;
    }

    public void setPassable(Set<Questionnaire> passable) {
        this.passable = passable;
    }

    public void addNotPass(Questionnaire questionnaire) {
        this.notPassable.add(questionnaire);
        questionnaire.getNotStudents().add(this);
    }

    public void deleteNotPassAndAddPass(Questionnaire questionnaire) {
        this.notPassable.remove(questionnaire);
        questionnaire.getNotStudents().remove(this);
        this.passable.add(questionnaire);
        questionnaire.getStudents().add(this);
    }

}
