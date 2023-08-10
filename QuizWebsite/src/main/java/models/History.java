package models;

import java.sql.Time;

public class History {
    private int historyId;
    private int userId;
    private int quizId;
    private double grade;
    private Time duration;

    /**
     * Constructor to initialize a History object with provided values.
     * @param historyId The ID of the history record.
     * @param userId The ID of the user associated with the history.
     * @param quizId The ID of the quiz associated with the history.
     * @param grade The grade or score achieved by the user in the quiz.
     * @param duration The duration of time spent by the user on the quiz.
     */
    public History(int historyId, int userId, int quizId, double grade, Time duration) {
        this.historyId = historyId;
        this.userId = userId;
        this.quizId = quizId;
        this.grade = grade;
        this.duration = duration;
    }

    /**
     * Constructor to initialize a History object with provided values.
     * @param userId The ID of the user associated with the history.
     * @param quizId The ID of the quiz associated with the history.
     * @param grade The grade or score achieved by the user in the quiz.
     * @param duration The duration of time spent by the user on the quiz.
     */
    public History(int userId, int quizId, double grade, Time duration) {
        this.userId = userId;
        this.quizId = quizId;
        this.grade = grade;
        this.duration = duration;
    }

    /**
     * Get the ID of the history record.
     * @return The history ID.
     */
    public int getHistoryId() {
        return historyId;
    }

    /**
     * Set the ID of the history record.
     * @param historyId The history ID to set.
     */
    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    /**
     * Get the ID of the user associated with the history.
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Get the ID of the quiz associated with the history.
     * @return The quiz ID.
     */
    public int getQuizId() {
        return quizId;
    }

    /**
     * Get the grade or score achieved by the user in the quiz.
     * @return The grade.
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Get the duration of time spent by the user on the quiz.
     * @return The duration.
     */
    public Time getDuration() {
        return duration;
    }
}
