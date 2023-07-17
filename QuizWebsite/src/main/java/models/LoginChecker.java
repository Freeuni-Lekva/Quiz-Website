package models;

import dao.UsersDao;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/*
 LoginChecker class takes three parameters in constructor:
 username, password and httpServletRequest.
 Using isCorrect() method checks if user username and password is correct or not.
 */

public class LoginChecker {
    private final String username;
    private final String password;
    private final HttpServletRequest httpServletRequest;

    public LoginChecker(String username, String password, HttpServletRequest httpServletRequest) {
        this.username = username;
        this.password = password;
        this.httpServletRequest = httpServletRequest;
    }

    /*
     Checks if user input(username, password) is correct
     then returns true, otherwise returns false
     */
    public boolean isCorrect() {
        ServletContext servletContext = httpServletRequest.getServletContext();
        UsersDao userInfo = (UsersDao) servletContext.getAttribute("users");
        Account userAccount = userInfo.getAccount(username);
        if (userAccount == null) {
            return false;
        }
        PasswordGenerator passwordGenerator = new PasswordGenerator(password);
        return userInfo.getPassword(username).equals(passwordGenerator.getHashedPassword());
    }
}