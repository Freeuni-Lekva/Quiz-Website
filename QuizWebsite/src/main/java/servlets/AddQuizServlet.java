package servlets;

import dao.AnswersDao;
import dao.QuestionsDao;
import dao.QuizDao;
import models.Question;
import models.QuestionBuilder;
import models.Quiz;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;

@WebServlet("/addQuizServlet")
public class AddQuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String quizName = (String) request.getSession().getAttribute("quizName");
            int numQuestions = (int) request.getSession().getAttribute("numQuestions");
            User user = (User) request.getSession().getAttribute("loggedUser");
            int userId = user.getId();
            System.out.println(quizName + numQuestions);
            // Create Quiz object
            Quiz quiz = new Quiz(
                    quizName,
                    "",
                    0,
                    true, // Set based on form input
                    false, // Set based on form input
                    false, // Set based on form input
                    userId
            );


            AnswersDao answersDao = (AnswersDao) request.getServletContext().getAttribute("answersDao");
            QuizDao quizDao = (QuizDao) request.getServletContext().getAttribute("quizzes");
            QuestionsDao questionsDao = (QuestionsDao) request.getServletContext().getAttribute("questionsDao");
            quizDao.addQuiz(quiz);

            for (int i = 1; i <= numQuestions; i++) {
                String questionText = request.getParameter("questionText" + i);
                String questionType = request.getParameter("questionType" + i);
                String pictureUrl = request.getParameter("pictureUrl" + i);
                String correctAnswerIndexStr = request.getParameter("correctAnswer" + i);
                int correctAnswerIndexInt = Integer.parseInt(correctAnswerIndexStr);
                String correctAnswer = request.getParameter("answer" + correctAnswerIndexInt + i);
                // Create Question using QuestionBuilder
                Question question = QuestionBuilder.create(
                        answersDao,
                        questionText,
                        questionType,
                        pictureUrl,
                        correctAnswer,
                        quiz.getId()
                );


                if (question != null) {
                    questionsDao.addQuestion(question);
                    String answer1 = request.getParameter("answer1" + i);
                    String answer2 = request.getParameter("answer2" + i);
                    String answer3 = request.getParameter("answer3" + i);
                    String answer4 = request.getParameter("answer4" + i);
                    answersDao.addAnswers(question.getQuestionId(), answer1, answer2, answer3, answer4);
                    quiz.addQuestion(question);
                }
            }

            response.sendRedirect("homepage.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("homepage.jsp");
        }
    }
}
