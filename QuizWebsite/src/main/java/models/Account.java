package models;
/**
 * The Account class represents a user account, including information such as the username, password,
 * user type, and account ID. It is used for registration, login, and retrieving information from the database
 * and then wrapping that information.
 */
public class Account {
    private String userName;
    private String password;
    private String userType;
    private int id;

    /**
     * Account constructor.
     * @param userName  The username of the account.
     * @param password  The password of the account.
     * @param userType  The type of the user account.
     */
    public Account(String userName, String password, String userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Sets the account ID.
     * @param id  The unique identifier for the account.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method is needed for security and passing Account with already hashed password
     * to the UsersDao class for adding new account to the DataBase.
     * @param hashedPassword already hashed password
     */
    public void setHashedPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

    /**
     * Retrieves the account ID.
     * @return The unique identifier for the account.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the account password.
     * @return The password of the account.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the account username.
     * @return The username of the account.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Retrieves the user type of the account.
     * @return The type of the user account.
     */
    public String getUserType() {
        return userType;
    }
}
