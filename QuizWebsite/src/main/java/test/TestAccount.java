package test;

import junit.framework.TestCase;
import models.Account;
import models.PasswordGenerator;
import org.junit.Test;

public class TestAccount extends TestCase {
    @Test
    public void testGetMethods() {
        Account user = new Account("jon", "1234", "user");
        assertTrue(user.getUserName().equals("jon"));
        assertTrue(user.getPassword().equals("1234"));
        assertTrue(user.getUserType().equals("user"));
        Account admin = new Account("alice", "5678", "admin");
        assertTrue(admin.getUserName().equals("alice"));
        assertTrue(admin.getPassword().equals("5678"));
        assertTrue(admin.getUserType().equals("admin"));
    }

    @Test
    public void testSetMethods() {
        Account user = new Account("jon", "1234", "user");
        user.setId(1);
        Account admin = new Account("alice", "5678", "admin");
        admin.setId(2);
        assertEquals(1, user.getId());
        assertEquals(2, admin.getId());
        user.setSalt("Ik7OoenhV8GNtgZb1LjSKg");
        assertTrue(user.getSalt().equals("Ik7OoenhV8GNtgZb1LjSKg"));
        String saltedPassword = user.getPassword() + user.getSalt();
        PasswordGenerator generator = new PasswordGenerator(saltedPassword);
        String hashedPassword = generator.getHashedPassword();
        user.setHashedPassword(hashedPassword);
        assertTrue(user.getPassword().equals(hashedPassword));
    }

    @Test
    public void testPasswords() {
        Account account = new Account("alice",
                "83ad639ec34a918055309da7189c2ca7fd5b8101",
                "admin", "f1ndNem0");
        String saltedPassword = "1234" + account.getSalt();
        PasswordGenerator generator = new PasswordGenerator(saltedPassword);
        assertTrue(account.getPassword().
                equals(generator.getHashedPassword()));
    }

}


