package servlets;

import dao.UsersDao;
import models.LoginChecker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/*
 When user welcomes /login url LoginServlet will be called.
 */
public class LoginServlet extends HttpServlet {
    /*
     When user welcomes /login url, LoginServlet doGet() method will be called.
     It calls login.jsp file and forwards httpRequest and httpResponse to it.
     */
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("login/login.jsp").forward(httpServletRequest, httpServletResponse);
    }

    /*
     When user inputs login info, doPost method is called.
     It checks if info is correct and acts appropriately.
     If it is correct, then user is logged in.
     If it is not, doPost method calls loginFailed.jsp and user should reenter
     username and password.
     */
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        ServletContext servletContext = httpServletRequest.getServletContext();
        UsersDao userInfo = (UsersDao) servletContext.getAttribute("users");
        LoginChecker check = new LoginChecker(username, password, userInfo);
        if (check.isCorrect()) {
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/homepage.jsp");
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("loggedUser", userInfo.getUser(username));
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } else {
            httpServletRequest.getRequestDispatcher("login/loginFailed.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
