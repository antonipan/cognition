package ru.antonio.cognition.models.questions;

import ru.antonio.cognition.models.answers.Answer;

import java.util.ArrayList;
import java.util.List;

public class ManyAnswerQuestion extends QuestionMain implements SelectQuestion{

    private List <Answer> answers;
    private ArrayList<Integer> arrayIndexTrueAnswers;

    public ManyAnswerQuestion(int orderNumber, String description,
                             double weightAnswer, int countTrueAnswers,
                             List <Answer> answers) {

        super(orderNumber, description, weightAnswer, countTrueAnswers);
        this.answers = answers;
    }

    public ManyAnswerQuestion() {

    }

    @Override
    public double checkingAnswerOfStudent(int [] answerStudent) {
        int countAnswerStudent = 0;
        for (int i = 0; i < answerStudent.length; i++) {
            if(arrayIndexTrueAnswers.contains(answerStudent[i])) {
                countAnswerStudent++;
            }
        }
        return weightAnswer * countAnswerStudent;
    }

    @Override
    public void setTrueAnswer(int [] arrayIndex) {
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

//    @Override
    public double calculateWeightAnswer(boolean answer) {
        return 0;
    }
}
