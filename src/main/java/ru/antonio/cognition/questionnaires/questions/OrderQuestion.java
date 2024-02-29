package ru.antonio.cognition.questionnaires.questions;

import ru.antonio.cognition.questionnaires.answers.Answer;

public interface OrderQuestion extends Question {

    double checkingAnswerOfStudent (Answer answer);

    void setTrueAnswer(int trueAnswer);
}
