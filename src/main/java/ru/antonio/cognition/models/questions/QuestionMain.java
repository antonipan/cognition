package ru.antonio.cognition.models.questions;

public abstract class QuestionMain {

    private int orderNumber;
    protected String description;
    protected double weightAnswer;
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

    public double getWeightAnswer() {
        return weightAnswer;
    }

    public void setWeightAnswer(double weightAnswer) {
        this.weightAnswer = weightAnswer;
    }

    public int getCountTrueAnswers() {
        return countTrueAnswers;
    }

    public void setCountTrueAnswers(int countTrueAnswers) {
        this.countTrueAnswers = countTrueAnswers;
    }
}
