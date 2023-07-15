package test;

import dao.UsersDao;
import junit.framework.TestCase;
import models.Account;
import models.Register;
import org.junit.Test;

public class TestRegister extends TestCase {
    class MockUsers extends UsersDao {
        @Override
        public void add(Account account) {}
        @Override
        public Account getAccount(String userName) {
            return new Account(userName, "123123", "user");
        }
    }
    @Test
    public void testRegister() {
        MockUsers users = new MockUsers();
        Register register = new Register(users);
        Account account = new Account("", "", null);
        assertFalse(register.registerAccount(account));
        account = new Account("", "", "");
        assertFalse(register.registerAccount(account));
        account = new Account("jon", "", "");
        assertFalse(register.registerAccount(account));
        account = new Account("jon", "1234", "");
        assertFalse(register.registerAccount(account));
        account = new Account("jon", "1234", null);
        assertFalse(register.registerAccount(account));
        account = new Account("jon", "1234", "user");
        assertTrue(register.registerAccount(account));
        account = new Account("alice", "1234567", "admin");
        assertTrue(register.registerAccount(account));
    }
}
