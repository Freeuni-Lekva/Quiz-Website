package models;

import dao.UsersDao;
import models.Account;
import models.PasswordGenerator;

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
     * @param account the Account object representing the user account to be registered.
     * @return true if the account was successfully registered, false otherwise.
     */
    public boolean registerAccount(Account account) {
        if (isValid(account)) {
            String salt = SaltGenerator.generateSalt();
            String saltedPassword = account.getPassword() + salt;
            PasswordGenerator generator = new PasswordGenerator(saltedPassword);
            String hashedPassword = generator.getHashedPassword();
            account.setHashedPassword(hashedPassword);
            users.add(account);
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
    private boolean isValid(Account account) {
        return account.getUserType() != null
                && !account.getUserType().isEmpty()
                && !account.getUserName().isEmpty()
                && !account.getPassword().isEmpty()
                && users.getAccount(account.getUserName()) == null;
    }
}
