package servlets;

import dao.ChallengeDao;
import models.Challenge;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChallengeServlet")
public class ChallengeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String username = request.getParameter("username");

        Challenge challenge = new Challenge(loggedUser.getUsername(), username, quizId);

        ChallengeDao challengeDao = (ChallengeDao) request.getServletContext().getAttribute("challenges");
        challengeDao.createChallenge(challenge);

        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
}
