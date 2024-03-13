package ru.antonio.cognition.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "questionnaires")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id", columnDefinition = "39")
    private Teacher author;

    @Column(name = "quantity_questions")
    private int quantityQuestions;

    @Column(name = "share_correct_answers", nullable = false)
    private double shareCorrectAnswers;

    @Transient
    private List<String> questions = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "workQuestionnaires", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @ManyToMany(mappedBy = "workQuestionnaires", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set <Teacher> teachers = new HashSet<>();


    public Questionnaire() {
    }

    public Questionnaire(String name, List <String> questions) {
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

    public Teacher getAuthor() {
        return author;
    }

    public void setAuthor(Teacher author) {
        this.author = author;
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

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    // WORK WITH COLLECTION OF TEACHERS

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
