package ru.antonio.cognition.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Set<Student> students = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set <Teacher> teachers = new HashSet<>();

    @OneToMany
    private Set<Questionnaire> questionnaires = new HashSet<>();

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

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                ", teachers=" + teachers +
                '}';
    }

    public Set<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Set<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public void addQuestToSubjects (Questionnaire questionnaire) {
        this.questionnaires.add(questionnaire);
    }

    public void deleteQuestToSubjects(Questionnaire questionnaire){
        this.questionnaires.remove(questionnaire);
    }
}
