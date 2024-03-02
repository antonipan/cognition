package ru.antonio.cognition.models.questions;

public interface SelectQuestion extends Question {

    double checkingAnswerOfStudent (int [] trueAnswer);

    void setTrueAnswer(int [] trueAnswer);

//    double calculateWeightAnswer (boolean answer);
}
