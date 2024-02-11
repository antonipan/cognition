package ru.antonio.cognition.models;

import java.util.ArrayList;

public class Question {

    private Long id;
    private String name;
    private String note;
    private QuestionType questionType;
    private ArrayList <Answer> answers;

    public Question(Long id, String name, String note, QuestionType questionType) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.questionType = questionType;
        this.answers = new ArrayList<>();
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
