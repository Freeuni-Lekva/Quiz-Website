package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

public class Quiz {
    private int id;
    private final String name;
    private final String description;
    private final int duration;
    private final boolean random_questions;
    private final boolean multiple_pages;
    private final boolean immediate_feedback;
    private final int author_id;
    private List<Question> questionList;
    private long startTime;
    private long endTime;


    public Quiz(int id, String name, String description, int duration, boolean random_questions,
                boolean multiple_pages, boolean immediate_feedback, int author_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.random_questions = random_questions;
        this.multiple_pages = multiple_pages;
        this.immediate_feedback = immediate_feedback;
        this.author_id = author_id;
        questionList = new ArrayList<>();
    }

    public Quiz(String name, String description, int duration, boolean random_questions,
                boolean multiple_pages, boolean immediate_feedback, int author_id) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.random_questions = random_questions;
        this.multiple_pages = multiple_pages;
        this.immediate_feedback = immediate_feedback;
        this.author_id = author_id;
        questionList = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return questionList;
    }

    public void addQuestions(List<Question> list) {
        questionList = list;
    }

    public void addQuestion(Question question) {
        questionList.add(question);
    }

    public void deleteQuestions() {
        questionList.clear();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public boolean randomQuestions() {
        return random_questions;
    }

    public boolean multiplePages() {
        return multiple_pages;
    }

    public boolean immediateFeedback() {
        return immediate_feedback;
    }

    public int getAuthorId() {
        return author_id;
    }

    public void startQuiz() {
        startTime = System.currentTimeMillis();
    }

    public void endQuiz() {
        endTime = System.currentTimeMillis();
    }

    public long calculateDuration() {
        return endTime - startTime;
    }
}
