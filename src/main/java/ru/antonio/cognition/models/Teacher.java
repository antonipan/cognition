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
    private Set<Subject> subjectSet = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "teach_of_stud",
            joinColumns = @JoinColumn(name = "teach_id"),
            inverseJoinColumns = @JoinColumn(name = "stud_id")
    )
    private Set<Student> studentSet = new HashSet<>();

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
        this.subjectSet = subjects;
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

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }

    public void addSubjectToSubjects (Subject subject) {
        this.subjectSet.add(subject);
    }
}
