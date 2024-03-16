package ru.antonio.cognition.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Класс сущности "Методика" {@link Questionnaire}
 *
 * @author Antonio
 * @version 1.0
 */
@Entity
@Table(name = "questionnaires")
public class Questionnaire implements Serializable {
// TODO: 16.03.2024 дописать документацию

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
    private List <String> questions = new ArrayList<>();

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

    public Questionnaire(String name, List <String> questions) {
        this.name = name;
        this.questions.addAll(questions);
        this.quantityQuestions = questions.size();
        this.shareCorrectAnswers = quantityQuestions*0.5;
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
        if(this.questions.size() == quantityQuestions) {
            this.quantityQuestions = quantityQuestions;
        } else {
            throw new IllegalArgumentException("No possible to set quantity questions");
        }
    }

    public double getShareCorrectAnswers() {
        return shareCorrectAnswers;
    }

    public void setShareCorrectAnswers(double shareCorrectAnswers) {
        this.shareCorrectAnswers = shareCorrectAnswers;
    }

    // WORK WITH COLLECTION OF QUESTIONS

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
        this.quantityQuestions = questions.size();
        this.shareCorrectAnswers = quantityQuestions*0.5;
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

    public void addQuestion (String question) {
        this.questions.add(question);
    }

    public void deleteQuestion(int number) {
        this.questions.remove(number);
    }
}
