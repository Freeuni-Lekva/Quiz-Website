package test;

import junit.framework.TestCase;
import models.PasswordGenerator;
import models.User;
import org.junit.Test;

public class TestUser extends TestCase {
    @Test
    public void testGetMethods() {
        User user = new User("jon", "1234", "user");
        assertTrue(user.getUsername().equals("jon"));
        assertTrue(user.getPassword().equals("1234"));
        assertTrue(user.getUserType().equals("user"));
        User admin = new User("alice", "5678", "admin");
        assertTrue(admin.getUsername().equals("alice"));
        assertTrue(admin.getPassword().equals("5678"));
        assertTrue(admin.getUserType().equals("admin"));
    }

    @Test
    public void testSetMethods() {
        User user = new User(1, "jon", "1234", "user", "");
        User admin = new User(2, "alice", "5678", "admin", "");
        assertEquals(1, user.getId());
        assertEquals(2, admin.getId());
        admin.setId(4);
        assertEquals(4, admin.getId());
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
        User account = new User("alice",
                "83ad639ec34a918055309da7189c2ca7fd5b8101",
                "admin", "f1ndNem0");
        String saltedPassword = "1234" + account.getSalt();
        PasswordGenerator generator = new PasswordGenerator(saltedPassword);
        assertTrue(account.getPassword().
                equals(generator.getHashedPassword()));
    }

}


