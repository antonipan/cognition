package ru.antonio.cognition.models;

import jakarta.persistence.*;
import ru.antonio.cognition.models.questions.Question;

import java.util.ArrayList;
import java.util.Collections;
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

    @Column(name = "questions")
    private List<Question> questions = new ArrayList<>();


    public Questionnaire(String name, List <Question> questions) {
        this.name = name;
        this.questions.addAll(questions);
    }

    public Questionnaire(String name) {
        this.name = name;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion (Question question) {
        this.questions.add(question);
    }

    public void deleteQuestion (Question question) {
        this.questions.remove(question);
    }

    public List<Question> mixedQuestions () {
        Collections.shuffle(this.questions);
        return this.questions;
    }



}
