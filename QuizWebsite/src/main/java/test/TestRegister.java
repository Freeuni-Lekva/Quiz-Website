package test;

import dao.UsersDao;
import junit.framework.TestCase;
import models.Register;
import models.User;
import org.junit.Test;

import java.sql.SQLException;

public class TestRegister extends TestCase {
    static class MockUsers extends UsersDao {
        @Override
        public void add(User account) {
        }

        @Override
        public User getUser(String userName) {
            return null;
        }
    }

    @Test
    public void testRegister() throws SQLException {
        MockUsers users = new MockUsers();
        Register register = new Register(users);
        User account = new User("", "", null);
        assertFalse(register.registerAccount(account));
        account = new User("", "", "");
        assertFalse(register.registerAccount(account));
        account = new User("jon", "", "");
        assertFalse(register.registerAccount(account));
        account = new User("jon", "1234", "");
        assertFalse(register.registerAccount(account));
        account = new User("jon", "1234", null);
        assertFalse(register.registerAccount(account));
        account = new User("jon", "1234", "user");
        assertTrue(register.registerAccount(account));
        account = new User("alice", "1234567", "admin");
        assertTrue(register.registerAccount(account));
    }
}
