package ru.antonio.cognition.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "stud_of_sub",
            joinColumns = @JoinColumn(name = "sub_id"),
            inverseJoinColumns = @JoinColumn(name = "stud_id")
    )
    private Set<Student> studentSet = new HashSet<>();

    @ManyToMany(mappedBy = "subjectSet")
    private Set <Teacher> teacherSet = new HashSet<>();

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }
}
