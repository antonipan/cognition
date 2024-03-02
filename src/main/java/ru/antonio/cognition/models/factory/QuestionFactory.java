package ru.antonio.cognition.models.factory;

import ru.antonio.cognition.controllers.questions.*;
import ru.antonio.cognition.models.questions.*;

public class QuestionFactory {

    private static QuestionFactory questionFactory;

    private QuestionFactory () {

    }

    public static QuestionFactory getQuestionFactory() {
        if (questionFactory == null) {
            questionFactory = new QuestionFactory();
        }
        return questionFactory;
    }

    public Question createQuestion (QuestionType questionType) {
        Question question = null;
        switch (questionType) {
            case ONE_ANSWER -> question = new OneAnswerQuestion();
            case MANY_ANSWER -> question = new ManyAnswerQuestion();
            case ORDER_ANSWER -> question = new OrderAnswerQuestion();
            case COMPLIANCE -> question = new ComplianceAnswer();
            default -> throw new NullPointerException("Вы пытаетесь создать несуществующий вопрос");
        }
        return question;
    }

}
