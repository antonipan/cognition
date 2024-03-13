package ru.antonio.cognition.models;

public class Answer {

    private int number;
    private String description;
    private boolean status;

    public Answer() {
    }

    public Answer(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public Answer(int number, String description, boolean status) {
        this.number = number;
        this.description = description;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
