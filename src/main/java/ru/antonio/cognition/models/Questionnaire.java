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

    @OneToMany(mappedBy = "questionId")
    private List<Question> questions = new ArrayList<>();


    public Questionnaire(String name, List <Question> questions) {
        this.name = name;
        this.questions.addAll(questions);
        this.shareCorrectAnswers = setShareCorrectAnswers();
        this.quantityQuestions = questions.size();
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

    public double setShareCorrectAnswers() {
        return weightMethodics(this.questions)*0.5;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion (Question question) {
        this.questions.add(question);
        this.shareCorrectAnswers = setShareCorrectAnswers();
        this.quantityQuestions = this.questions.size();
    }

    public void deleteQuestion (Question question) {
        this.questions.remove(question);
        this.shareCorrectAnswers = setShareCorrectAnswers();
        this.quantityQuestions = this.questions.size();
    }


    public List<Question> mixedQuestions () {
        Collections.shuffle(this.questions);
        int count = 0;
        for (Question q: questions
             ) {
            q.setNumberQuestion(count);
        }
        return this.questions;
    }

    public double weightMethodics (List <Question> questions) {
        return questions.stream().mapToDouble(Question::getWeightAnswer).sum();
    }

}
