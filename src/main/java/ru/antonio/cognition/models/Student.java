package ru.antonio.cognition.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends User {

    private String name;
    private Integer school;
    private String grade;
    private Double progress;

    @ManyToMany(mappedBy = "studentSet")
    private Set<Subject> subjectSet = new HashSet<>();

    @ManyToMany(mappedBy = "studentSet")
    private Set<Teacher> teacherSet = new HashSet<>();

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
        this.progress = progress;
    }

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }
}
