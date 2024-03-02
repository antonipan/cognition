package ru.antonio.cognition.models.questions;

import ru.antonio.cognition.models.answers.Answer;

import java.util.ArrayList;
import java.util.List;

public class OrderAnswerQuestion extends QuestionMain implements SelectQuestion {
    private List <Answer> answers;
    private ArrayList <Integer> arrayIndexTrueAnswers;

    public OrderAnswerQuestion(int orderNumber, String description, double weightAnswer, int countTrueAnswers) {
        super(orderNumber, description, weightAnswer, countTrueAnswers);
    }

    public OrderAnswerQuestion() {

    }

    @Override
    public double checkingAnswerOfStudent(int[] trueAnswer) {
        return 0;
    }

    @Override
    public void setTrueAnswer(int[] arrayIndex) {
        try {
            if(arrayIndex.length > this.countTrueAnswers) {
                throw new RuntimeException();
            }
            for (int i = 0; i < this.arrayIndexTrueAnswers.size(); i++) {
                this.arrayIndexTrueAnswers.set(i, arrayIndex[i]);
            }
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }
}
