package ru.antonio.cognition.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User {

    private String name;
    private Integer school;
    private String grade;
    private Double progress;

    public Student(String username, String password, Role role, String name, Integer school, String grade) {
        super(username, password, role);
        this.name = name;
        this.school = school;
        this.grade = grade;
        this.progress = 0.0;
    }

    public Student(String username, String password, String name, Integer school, String grade) {
        super(username, password);
        this.name = name;
        this.school = school;
        this.grade = grade;
        this.progress = 0.0;
    }

    public Student(String name, Integer school, String grade, Double progress) {
        this.name = name;
        this.school = school;
        this.grade = grade;
        this.progress = progress;
    }

    public Student() {
    }

    public Student(String username, String password, Role role) {
        super(username, password, role);
        this.name = username;
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
}
