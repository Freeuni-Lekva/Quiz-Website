package models;

import dao.AnswersDao;

import java.util.List;

/**
 * The QuestionBuilder class is responsible for creating instances of Question subclasses based on
 * provided parameters and data from the AnswersDao.
 */
public class QuestionBuilder {

    /**
     * Creates a new Question instance based on the provided parameters and data from the AnswersDao.
     *
     * @param answersDao      The DAO used to retrieve answers for the question.
     * @param questionId      The unique identifier for the question.
     * @param questionContent The content or text of the question.
     * @param questionType    The type of the question, indicating the Question subclass to instantiate.
     * @param pictureUrl      The URL of an associated picture (used for PictureQuestion).
     * @param quizId          The unique identifier of the quiz this question belongs to.
     * @return A Question instance of an appropriate subclass, or null if questionType is unsupported.
     */
    public static Question create(AnswersDao answersDao,
                                  int questionId,
                                  String questionContent,
                                  String questionType,
                                  String pictureUrl,
                                  String answer,
                                  int quizId) {
//        List<String> answers = answersDao.getAnswers(questionId);
        Question question;
        switch (questionType) {
            case QuestionType.PICTURE_QUESTION:
                question = new PictureQuestion(questionContent, pictureUrl, questionType);
                setRests(question, questionId, quizId, answer);
                break;
//            case QuestionType.QUESTION_RESPONSE:
//            case QuestionType.FILL_THE_BLANK:
//                question = new SingleQuestion(questionContent, questionType);
//                setRests(question, questionId, quizId, answers);
//                break;
            case QuestionType.MULTIPLE_CHOICE:
                question = new MultipleQuestion(questionContent, questionType);
                setRests(question, questionId, quizId, answer);
                break;
            default:
                question = null;
        }

        return question;
    }


    public static Question create(AnswersDao answersDao,
                                  String questionContent,
                                  String questionType,
                                  String pictureUrl,
                                  String answer,
                                  int quizId) {
        Question question;
        switch (questionType) {
            case QuestionType.PICTURE_QUESTION:
                question = new PictureQuestion(questionContent, pictureUrl, questionType);
                setRests(question, quizId, answer);
                break;
//            case QuestionType.QUESTION_RESPONSE:
//            case QuestionType.FILL_THE_BLANK:
//                question = new SingleQuestion(questionContent, questionType);
//                setRests(question, quizId);
//            break;
            case QuestionType.MULTIPLE_CHOICE:
                question = new MultipleQuestion(questionContent, questionType);
                setRests(question, quizId, answer);
                break;
            default:
                question = null;
        }

        return question;
    }

    /**
     * Sets additional properties of the Question instance, such as quizId and answers.
     *
     * @param question The Question instance to configure.
     * @param quizId   The unique identifier of the quiz this question belongs to.
     * @param answers  The list of answers associated with the question.
     */
    private static void setRests(Question question, int questionId, int quizId, String answer) {
        question.setQuestionId(questionId);
        question.setQuizId(quizId);
        question.addAnswer(answer);
    }

    private static void setRests(Question question, int quizId, String answer) {
        question.setQuizId(quizId);
        question.addAnswer(answer);
    }
}
