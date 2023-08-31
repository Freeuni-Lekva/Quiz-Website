package servlets;

import dao.QuizDao;
import models.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/QuizServlet")
public class QuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String strId = httpServletRequest.getParameter("id");
        QuizDao quizDao = (QuizDao) httpServletRequest.getServletContext().getAttribute("quizzes");
        int id = Integer.parseInt(strId);
        Quiz quiz = null;
        try {
            quiz = quizDao.getQuiz(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        httpServletRequest.setAttribute("quiz", quiz);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("quiz", quiz);
        quiz.startQuiz();
        httpServletRequest.getRequestDispatcher("quiz.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
//        String strId = httpServletRequest.getParameter("id");
//        QuizDao quizDao = (QuizDao) httpServletRequest.getServletContext().getAttribute("quizzes");
//        int id = Integer.parseInt(strId);
//        Quiz quiz = null;
//        try {
//            quiz = quizDao.getQuiz(id);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        httpServletRequest.setAttribute("quiz", quiz);
//        HttpSession session = httpServletRequest.getSession();
//        session.setAttribute("quiz", quiz);
//        quiz.startQuiz();
//        httpServletRequest.getRequestDispatcher("quiz.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
