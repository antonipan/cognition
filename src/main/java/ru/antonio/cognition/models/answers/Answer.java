package ru.antonio.cognition.models.answers;

public class Answer {
    private int id;
    private int numberAnswer;
    private String description;

    public Answer(int numberAnswer, String description) {
        this.numberAnswer = numberAnswer;
        this.description = description;
    }

    public Answer () {

    }

    public int getNumberAnswer() {
        return numberAnswer;
    }

    public void setNumberAnswer(int numberAnswer) {
        this.numberAnswer = numberAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
