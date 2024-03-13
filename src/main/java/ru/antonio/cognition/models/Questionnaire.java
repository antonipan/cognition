package ru.antonio.cognition.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "questionnaires")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Teacher author;

    @Column(name = "quantity_questions", nullable = true)
    private int quantityQuestions;

    @Column(name = "share_correct_answers", nullable = false)
    private double shareCorrectAnswers;

    @Transient
    private List<String> questions = new ArrayList<>();

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

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
}
