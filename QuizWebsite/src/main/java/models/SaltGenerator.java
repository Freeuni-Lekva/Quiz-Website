package models;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * The SaltGenerator class provides methods to generate
 * a secure random salt for password hashing.
 */
public class SaltGenerator {
    private static final int SALT_LENGTH = 16; // Length of the salt in bytes
    /**
     * Generates a unique and secure salt for password hashing.
     * @return The generated salt as a Base64-encoded string.
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
