package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed for multiple choice question types.
 * Representation value:
 * all possible choices visible for user is store in the List
 * answer is only one and store in the private string
 */
public class MultipleQuestion extends Question implements Serializable {
    private List<String> choices;
    private String answer;
    public MultipleQuestion(String question, String type) {
        super(question, type);
        choices = new ArrayList<>();
    }

    /**
     * This method evaluates and checks if passed string is the answer
     * @param userAnswer in case of the multiple choice question should only be passed one string
     * @return if the answer is correct output is 1 elsewhere 0
     */
    @Override
    public double gradeAnswer(String userAnswer) {
        return this.answer.equals(userAnswer) ? 1 : 0;
    }

    /**
     * This method assigns answer to this question. As answer can be only one
     * in passed array should be only one string in 0 index
     * @param answers Variable-length argument representing the correct answers to be added.
     */
    @Override
    public void addAnswer(String... answers) {
        answer = answers[0];
    }

    @Override
    public String getAnswer() {
        return this.answer;
    }

    /**
     * This method adds possible choices to the object and saves in the list.
     * @param choices array of String adding them to instance variable
     */
    public void addChoice(String... choices) {
        for (String choice : choices) {
            this.choices.add(choice);
        }
    }

    public List<String> getChoices() {
        return choices;
    }
}
