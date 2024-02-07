package ru.antonio.cognition.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questionnaires")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "quantity_questions", nullable = true)
    private int quantityQuestions;

    @Column(name = "share_correct_answers", nullable = false)
    private double shareCorrectAnswers;

    public Questionnaire(String name, String author, int quantityQuestions, double shareCorrectAnswers) {
        this.name = name;
        this.author = author;
        this.quantityQuestions = quantityQuestions;
        this.shareCorrectAnswers = shareCorrectAnswers;
    }

    public Questionnaire(String name, String author, int quantityQuestions) {
        this.name = name;
        this.author = author;
        this.quantityQuestions = quantityQuestions;
    }

    public Questionnaire() {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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
}
