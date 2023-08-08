package models;

import dao.UsersDao;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/*
 LoginChecker class takes three parameters in constructor:
 username, password and httpServletRequest.
 Using isCorrect() method checks if user username and password is correct or not.
 */

public class LoginChecker {
    private final String username;
    private final String password;
    private final UsersDao userInfo;

    public LoginChecker(String username, String password, UsersDao userInfo) {
        this.username = username;
        this.password = password;
        this.userInfo = userInfo;
    }

    /*
     Checks if user input(username, password) is correct
     then returns true, otherwise returns false
     */
    public boolean isCorrect() {
        User userAccount = userInfo.getUser(username);
        if (userAccount == null) {
            return false;
        }
        PasswordGenerator passwordGenerator = new PasswordGenerator(password + userAccount.getSalt());
        return userAccount.getPassword().equals(passwordGenerator.getHashedPassword());
    }
}
