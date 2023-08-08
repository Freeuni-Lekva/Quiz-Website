package models;

import dao.UsersDao;
import models.PasswordGenerator;

import java.sql.SQLException;

/**
 * The Register class is responsible for handling the registration of user accounts.
 * It provides methods to validate and register user accounts using a UsersDao object.
 */
public class Register {
    private UsersDao users;

    /**
     * Constructs a new Register object with the specified UsersDao.
     *
     * @param users the UsersDao object used for accessing and manipulating user accounts.
     */
    public Register(UsersDao users) {
        this.users = users;
    }

    /**
     * Registers a new user account in the database after checking validity and hasing.
     *
     * @param user the Account object representing the user account to be registered.
     * @return true if the account was successfully registered, false otherwise.
     */
    public boolean registerAccount(User user) {
        if (isValid(user)) {
            String salt = SaltGenerator.generateSalt();
            String saltedPassword = user.getPassword() + salt;
            user.setSalt(salt);
            PasswordGenerator generator = new PasswordGenerator(saltedPassword);
            String hashedPassword = generator.getHashedPassword();
            user.setHashedPassword(hashedPassword);
            users.add(user);
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified Account object is valid for registration.
     * An account is considered valid if it has a non-empty user type, username, password,
     * and if the username doesn't already exist in the UsersDao.
     *
     * @param account the Account object to be validated.
     * @return true if the account is valid, false otherwise.
     */
    private boolean isValid(User account) {
        return account.getUserType() != null
                && !account.getUserType().isEmpty()
                && !account.getUsername().isEmpty()
                && !account.getPassword().isEmpty()
                && users.getUser(account.getUsername()) == null;
    }
}
