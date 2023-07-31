package servlets;

import dao.UsersDao;
import models.Register;
import models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doGet(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        UsersDao users = (UsersDao) servletContext.getAttribute("users");
        String userName = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        String userType = httpServletRequest.getParameter("select");
        Register register = new Register(users);
        User user = new User(userName, password, userType);
        RequestDispatcher dispatcher;

        if (register.registerAccount(user)) {
            dispatcher = servletContext.getRequestDispatcher("/register/RegisterSuccessful.jsp");
        } else {
            dispatcher = servletContext.getRequestDispatcher("/register/RegisterFailed.jsp");
        }
        dispatcher.forward(httpServletRequest, httpServletResponse);
    }
}
