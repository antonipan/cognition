package ru.antonio.cognition.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "questionnaires")
public class Questionnaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity_questions")
    private int quantityQuestions;

    @Column(name = "share_correct_answers", nullable = false)
    private double shareCorrectAnswers;

    @Column(name = "questions")
    private List<Question> questions = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "questionnaires", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "workQuestionnaires", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set <Teacher> teachers = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "notPassable", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set <Student> notStudents = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "passable", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set <Student> students = new HashSet<>();


    public Questionnaire() {
    }

    public Questionnaire(String name, List <Question> questions) {
        this.name = name;
        this.questions.addAll(questions);
        this.quantityQuestions = questions.size();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityQuestions() {
        return quantityQuestions;
    }

    public void setQuantityQuestions(int quantityQuestions) {
        this.quantityQuestions = quantityQuestions;
    }

    public double getShareCorrectAnswers() {
        return shareCorrectAnswers;
    }

    public void setShareCorrectAnswers(double shareCorrectAnswers) {
        this.shareCorrectAnswers = shareCorrectAnswers;
    }

    // WORK WITH COLLECTION OF QUESTIONS

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    // WORK WITH COLLECTION OF TEACHERS

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Student> getNotStudents() {
        return notStudents;
    }

    public void setNotStudents(Set<Student> notStudents) {
        this.notStudents = notStudents;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addQuestion (Question question) {
        this.questions.add(question);
    }

    public void deleteQuestion(int number) {
        this.questions.remove(number);
    }
}
