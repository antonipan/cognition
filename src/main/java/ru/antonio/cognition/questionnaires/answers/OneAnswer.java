package ru.antonio.cognition.questionnaires.answers;

public class OneAnswer extends Answer {
    private boolean status;


    public OneAnswer(int numberAnswer, String description, boolean status) {
        super(numberAnswer, description);
        this.status = status;
    }
}
