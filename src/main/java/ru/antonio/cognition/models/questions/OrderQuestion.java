package ru.antonio.cognition.models.questions;

import ru.antonio.cognition.models.answers.Answer;

public interface OrderQuestion extends Question {

    double checkingAnswerOfStudent (Answer answer);

    void setTrueAnswer(int trueAnswer);
}
