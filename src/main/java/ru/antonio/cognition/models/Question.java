package ru.antonio.cognition.models;

import java.util.ArrayList;

public class Question {

    private int number;
    private String name;
    private ArrayList <Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Question(String name, ArrayList<Answer> answers) {
        this.name = name;
        this.answers = answers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
