package ru.antonio.cognition.questionnaires.questions;

import org.springframework.expression.ParseException;
import ru.antonio.cognition.questionnaires.answers.Answer;
import ru.antonio.cognition.questionnaires.factory.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class OneAnswerQuestion extends QuestionMain
        implements SelectQuestion {

    private List <Answer> answers;
    private ArrayList <Integer> arrayIndexTrueAnswers;

    public OneAnswerQuestion(int orderNumber, String description,
                             double weightAnswer, int countTrueAnswers,
                             List <Answer> answers) {

        super(orderNumber, description, weightAnswer, countTrueAnswers);
        this.answers = answers;
    }

    public OneAnswerQuestion() {
        super();
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
    public double calculateWeightAnswer (boolean answer) {
        return answer ? 1 : 0;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


}
