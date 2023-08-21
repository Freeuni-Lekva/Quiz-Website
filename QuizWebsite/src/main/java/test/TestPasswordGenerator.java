package test;

import junit.framework.TestCase;
import org.junit.Test;
import models.PasswordGenerator;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestPasswordGenerator extends TestCase {
    @Test
    public void testPasswordHashing() {
        String password = "myStrongPassword";
        PasswordGenerator passwordGenerator = new PasswordGenerator(password);

        String hashedPassword = passwordGenerator.getHashedPassword();

        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
        assertTrue("e38ad214943daad1d64c102faec29de4afe9da3d"
                .equals(new PasswordGenerator("password1").getHashedPassword()));
        assertTrue("eacd2617f105704f51c912099316c7aece2df8ef"
                .equals(new PasswordGenerator("giorgi").getHashedPassword()));
        assertTrue("90fa18f75036f7a6833022ab246c6ee47000912f"
                .equals(new PasswordGenerator("tom123").getHashedPassword()));

        PasswordGenerator passwordGenerator2 = new PasswordGenerator(password);
        String hashedPassword2 = passwordGenerator2.getHashedPassword();
        assertEquals(hashedPassword, hashedPassword2);
    }

    @Test
    public void testHexToStringConversion() {
        byte[] bytes = {(byte) 0x12, (byte) 0x34, (byte) 0xAB, (byte) 0xCD};
        String expectedHex = "1234abcd";

        String hexString = PasswordGenerator.hexToString(bytes);

        assertEquals(expectedHex, hexString);
    }
}
