package test;

import junit.framework.TestCase;
import models.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestQuestions extends TestCase {
    @Test
    public void testMultiple() {
        MultipleQuestion question = new MultipleQuestion("who was persident of us in 1987?", QuestionType.MULTIPLE_CHOICE);
        question.addAnswer("Reagan");
        question.addChoice("Reagan", "Bush", "Biden", "Obama");
        assertEquals(4, question.getChoices().size());
        assertEquals(1.0, question.gradeAnswer("Reagan"));
        question.addChoice("Trump", "Lincoln");
        assertEquals(1.0, question.gradeAnswer("Reagan"));
        question.addAnswer("Reagan");
        assertEquals(1.0, question.gradeAnswer("Reagan"));
        assertEquals(0.0, question.gradeAnswer("Bush"));
    }

    @Test
    public void testSingle() {
        Question question = new SingleQuestion("What is the capital of us?", QuestionType.QUESTION_RESPONSE);
        question.addAnswer("Washington", "Washington D.C");
        assertEquals(1.0, question.gradeAnswer("Washington"));
        assertEquals(0.0, question.gradeAnswer("nyc"));
    }

    @Test
    public void testPicture() {
        PictureQuestion question
                = new PictureQuestion("name this molecule",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQu5a58yLUTmyNlkDUSmLOZNDsVawJAsjnD_V-L8o5eeA&s",
                QuestionType.PICTURE_QUESTION);
        question.addAnswer("H2O");
        assertNotNull(question.getQuestion());
        assertNotNull(question.getImgUrl());
        assertEquals(1.0, question.gradeAnswer("H2O"));
        assertEquals(0.0, question.gradeAnswer("CH2"));
        PictureQuestion question1
                = new PictureQuestion("https://img.freepik.com/free-photo/white-horse-runs-beach_1340-34263.jpg?w=2000", QuestionType.PICTURE_QUESTION);
        question1.addAnswer("horse");
        assertEquals(1.0, question1.gradeAnswer("horse"));
    }
}
