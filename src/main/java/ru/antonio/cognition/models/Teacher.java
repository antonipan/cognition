package ru.antonio.cognition.models;

import jakarta.persistence.*;


@Entity
@Table(name = "teachers")
public class Teacher extends User {

    private String name;
    private String subject;
    private Integer experience;

    public Teacher() {
    }

    public Teacher(String username, String password, Role role, String name, String subject, Integer experience) {
        super(username, password, role);
        this.name = name;
        this.subject = subject;
        this.experience = experience;
    }

    public Teacher(String username, String password, String name, String subject, Integer experience) {
        super(username, password);
        this.name = name;
        this.subject = subject;
        this.experience = experience;
    }

    public Teacher(String name, String subject, Integer experience) {
        this.name = name;
        this.subject = subject;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
