package servlets;

import models.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/createQuizServlet")
public class CreateQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.sendRedirect("createQuiz.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String quizName = httpServletRequest.getParameter("quizName");
        int numQuestions = (int) Integer.parseInt(httpServletRequest.getParameter("numQuestions"));

        httpServletRequest.getSession().setAttribute("quizName", quizName);
        httpServletRequest.getSession().setAttribute("numQuestions", numQuestions);
        httpServletRequest.getRequestDispatcher("createQuestions.jsp").forward(httpServletRequest, httpServletResponse);
    }
}