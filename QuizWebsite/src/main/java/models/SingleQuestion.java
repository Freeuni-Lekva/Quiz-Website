package models;

import java.util.HashSet;
/**
 * This class represents a single-choice question that can handle two types of questions:
 * Question-Response and Fill the blank types. As these questions could have multiple correct answers,
 * we save them in the HashSet.
 */
public class SingleQuestion extends Question {

    private HashSet<String> answers;

    /**
     * Constructs a new SingleQuestion object with the given question text.
     *
     * @param question The question text to be displayed.
     */
    public SingleQuestion(String question, String type) {
        super(question, type);
        answers = new HashSet<>();
    }

    /**
     * Grades the provided answers and returns the score.
     *
     * @param userAnswer single string representing answer of the user for checking it.
     * @return The grade/score for the given answers.
     */
    @Override
    public double gradeAnswer(String userAnswer) {
        return answers.contains(userAnswer) ? 1 : 0;
    }

    /**
     * Adds one or more correct answers to the question.
     *
     * @param answers One or more strings representing correct answers to the question.
     *
     * The method allows adding one or more correct answers to the question.
     * Each correct answer provided will be stored in the HashSet to be used for grading later.
     */
    @Override
    public void addAnswer(String... answers) {
        for (String answer : answers) {
            this.answers.add(answer); // Add each correct answer to the HashSet.
        }
    }

    @Override
    public String getAnswer() {
        return null;
    }
}
