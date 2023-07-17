package servlets;

import models.LoginChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        LoginChecker check = new LoginChecker(username, password, httpServletRequest);
        if (check.isCorrect()) {

        } else {
            httpServletRequest.getRequestDispatcher("login/loginFailed.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}