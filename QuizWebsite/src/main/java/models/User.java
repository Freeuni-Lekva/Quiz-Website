package models;
/**
 * The User class represents a user account, including information such as the username, password,
 * user type, and user ID. It is used for registration, login, and retrieving information from the database
 * and then wrapping that information.
 */
public class User {
    private String username;
    private String password;
    private String userType;
    private String salt;
    private int id;

    /**
     * User constructor.
     * @param username  The username of the user.
     * @param password  The password of the user.
     * @param userType  The type of the user.
     */
    public User(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Account constructor for getting data from database when salt is available
     * and stored for this account
     * @param username  The username of the user.
     * @param password  The password of the user.
     * @param userType  The type of the user.
     * @param salt The salt which was created and saved after registration
     */
    public User(String username, String password, String userType, String salt) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.salt = salt;
    }

    public User(int id, String username, String password, String userType, String salt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.salt = salt;
    }

    /**
     * This method is setting generated salt for the user. lately it would be saved on db
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * This method returns already generated salt of a user
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the user ID.
     * @param id  The unique identifier for the account.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method is needed for security and passing user with already hashed password
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
     * Retrieves the user password.
     * @return The password of the account.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the username.
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the user type of the user.
     * @return The type of the user.
     */
    public String getUserType() {
        return userType;
    }

}