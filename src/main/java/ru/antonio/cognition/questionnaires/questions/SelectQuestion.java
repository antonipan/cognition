package ru.antonio.cognition.questionnaires.questions;

import ru.antonio.cognition.questionnaires.answers.Answer;

public interface SelectQuestion extends Question {

    double checkingAnswerOfStudent (int [] trueAnswer);

    void setTrueAnswer(int [] trueAnswer);

//    double calculateWeightAnswer (boolean answer);
}
