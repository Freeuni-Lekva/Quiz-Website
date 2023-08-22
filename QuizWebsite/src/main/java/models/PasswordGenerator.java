package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class generates hashed passwords for the passed password
 * in constructor. class is needed for registration and
 * verifying the authenticity of a user's credentials
 */
public class PasswordGenerator {
    private String password;

    /**
     * Constructor for creating instance of this class with associated password
     * @param password that should be hashed and returned in other method
     */
    public PasswordGenerator(String password) {
        this.password = password;
    }

    /**
     * This method hashes field password with SHA algorithm
     * @return hashed password
     */
    public String getHashedPassword() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            return hexToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    /*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
    public static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i=0; i<bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val<16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }
}
