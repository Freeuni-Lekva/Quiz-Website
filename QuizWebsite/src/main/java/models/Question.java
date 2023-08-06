package models;

/**
 * The abstract base class for different types of questions in a quiz or assessment system.
 * Classes that extend this class will handle the logic and implementation of specific question types,
 * such as Question-Response, Fill the Blank, Multiple Choice, and Picture-Response types of questions.
 * Each subclass can use different types for saving real answers.
 */
public abstract class Question {

    private String question;

    /**
     * Constructor for the Question class.
     *
     * @param question The text of the question.
     */
    public Question(String question) {
        this.question = question;
    }

    /**
     * Abstract method to grade the given answers for the question.
     * can be modified easily to handle extension for multi answer questions
     * @param userAnswer passed argument would be checked with real value.
     * @return A double value representing the grade or score for the given answers. .
     */
    public abstract double gradeAnswer(String userAnswer);

    /**
     * Abstract method to add correct answers to the question.
     * Subclasses implementing this method should handle the storage and management of real answers
     * according to the specific type of question.
     *
     * @param answers Variable-length argument representing the correct answers to be added.
     */
    public abstract void addAnswer(String... answers);

    /**
     * Getter method to retrieve the text of the question.
     *
     * @return A String representing the question text.
     */
    public String getQuestion() {
        return question;
    }
}
