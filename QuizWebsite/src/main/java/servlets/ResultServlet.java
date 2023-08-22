package servlets;

import models.MultipleQuestion;
import models.Question;
import models.QuestionType;
import models.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Quiz quiz = (Quiz) httpServletRequest.getSession().getAttribute("quiz");
        quiz.endQuiz();
        long time = quiz.calculateDuration();
        boolean failed = true;
        long duration = time / 60000;
        if (time <= quiz.getDuration() * 60000L) {
            failed = false;
        }
        List<Question> list = (List<Question>) httpServletRequest.getSession().getAttribute("questions");
        double sum = (double) 0;
        int n = 1;
        for (int i = 0; i < list.size(); i++) {
            String answer = "";
            Question question = list.get(i);
            if (question.getType().equals(QuestionType.MULTIPLE_CHOICE)) {
                MultipleQuestion multipleQuestion = (MultipleQuestion) question;
                for (int k = 0; k < multipleQuestion.getChoices().size(); k++) {
                    String answer1 = httpServletRequest.getParameter("answer" + n);
                    if (answer1 != null && !answer1.isEmpty()) {
                        answer = answer1;
                        System.out.println(answer1);

                    }
                    n++;
                }
            } else {
                answer = httpServletRequest.getParameter("answer" + n);
                System.out.println(n);
                n++;
            }
            double score = question.gradeAnswer(answer);
            sum += score;
        }
        String quizName = quiz.getName();
        httpServletRequest.setAttribute("quiz", quiz);
        httpServletRequest.setAttribute("quizName", quizName);
        httpServletRequest.setAttribute("failed", failed);
        httpServletRequest.setAttribute("sum", sum);
        httpServletRequest.setAttribute("duration", duration);
        httpServletRequest.getRequestDispatcher("quizResult.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
