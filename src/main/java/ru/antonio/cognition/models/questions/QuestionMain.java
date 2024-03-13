package ru.antonio.cognition.models.questions;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class QuestionMain implements Question{

    @EmbeddedId
    @ManyToOne()
    @JoinColumn(name = "questionnaire_id")
    private Long questionId;
    private int orderNumber;
    protected String description;
    protected double weightAnswer;
    protected int numberQuestion;
    protected int countTrueAnswers;

    public QuestionMain(int orderNumber, String description, double weightAnswer, int countTrueAnswers) {
        this.orderNumber = orderNumber;
        this.description = description;
        this.weightAnswer = weightAnswer;
        this.countTrueAnswers = countTrueAnswers;
    }

    public QuestionMain() {

    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getWeightAnswer() {
        return weightAnswer;
    }

    public void setWeightAnswer(double weightAnswer) {
        this.weightAnswer = weightAnswer;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    @Override
    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public int getCountTrueAnswers() {
        return countTrueAnswers;
    }

    public void setCountTrueAnswers(int countTrueAnswers) {
        this.countTrueAnswers = countTrueAnswers;
    }
}
