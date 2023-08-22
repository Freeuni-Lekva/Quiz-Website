package test;

import dao.UsersDao;
import junit.framework.TestCase;
import models.LoginChecker;
import models.PasswordGenerator;
import models.User;
import org.junit.Test;

import java.util.HashMap;

public class TestLogin extends TestCase {
    private class MockUsersDao extends UsersDao {
        private HashMap<String, User> users;
        public MockUsersDao() {
            users = new HashMap<>();
        }
        @Override
        public void add(User user) {
            users.put(user.getUsername(), user);
        }

        @Override
        public User getUser(String username) {
            return users.get(username);
        }
    }

    @Test
    public void testCorrectness() {
        MockUsersDao mockUsers = new MockUsersDao();
        PasswordGenerator passwordGenerator = new PasswordGenerator("gio123" + "123");
        User user = new User("giorgi", passwordGenerator.getHashedPassword(), "user", "123");
        mockUsers.add(user);
        LoginChecker loginChecker = new LoginChecker(user.getUsername(), "gio123", mockUsers);
        assertTrue(loginChecker.isCorrect());
        loginChecker = new LoginChecker(user.getUsername(), "gio", mockUsers);
        assertFalse(loginChecker.isCorrect());
        loginChecker = new LoginChecker("nika", "gio", mockUsers);
        assertFalse(loginChecker.isCorrect());
    }
}
