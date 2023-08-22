package dao;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The UsersDao class is responsible for interacting with the database to perform CRUD operations
 * related to the 'users' table, which stores user information.
 */
public class UsersDao {
    private Connection conn;

    /**
     * Constructs a UsersDao instance with a database connection.
     * @param conn The database connection to be used for database interactions.
     */
    public UsersDao(Connection conn) {
        this.conn = conn;
    }
    /**
    * Default constructor for UsersDao.
    */
    public UsersDao() {

    }
    /**
     * Adds a new user to the database.
     * @param user The User object containing user information to be added.
     * @throws RuntimeException if a SQL exception occurs during the database operation.
     */
    public void add(User user) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, user_type, salt) VALUES (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserType());
            ps.setString(4, user.getSalt());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            user.setId(id);
        } catch (SQLException e) {}
    }
    /**
     * Retrieves a user from the database based on the given username.
     * @param username The username of the user to be retrieved.
     * @return The User object representing the retrieved user, or null if the user doesn't exist.
     * @throws RuntimeException if a SQL exception occurs during the database operation.
     */
    public User getUser(String username) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("user_type"), rs.getString("salt"));
            }
        } catch (SQLException e) {}
        return null;
    }
    /**
     * Retrieves a user from the database based on the given user ID.
     * @param userId The ID of the user to be retrieved.
     * @return The User object representing the retrieved user, or null if the user doesn't exist.
     * @throws RuntimeException if a SQL exception occurs during the database operation.
     */
    public User getUser(int userId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("user_type"), rs.getString("salt"));
            }
        } catch (SQLException e) {}
        return  null;
    }
    /**
     * Searches for users with usernames that match the given prefix.
     * @param usernamePrefix The prefix to search for in usernames.
     * @return A list of User objects representing the matching users.
     * @throws RuntimeException if a SQL exception occurs during the database operation.
     */
    public List<User> searchPrefix(String usernamePrefix) {
        ArrayList<User> userList = new ArrayList<>();
        if (usernamePrefix.isEmpty()) {
            return userList;
        }
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username LIKE ?");
            ps.setString(1,  usernamePrefix + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("user_type"),
                        rs.getString("salt")));
            }
        } catch (SQLException e) {}
        return userList;
    }
    /**
     * Removes a user from the database based on the given username.
     * @param username The username of the user to be removed.
     * @throws RuntimeException if a SQL exception occurs during the database operation.
     */
    public void removeUser(String username) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("DELETE FROM users WHERE username = ?");
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {}
    }

    public void removeUser(int userId) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("DELETE FROM users WHERE user_id = ?");
            statement.setString(1, String.valueOf(userId));
            statement.executeUpdate();
        } catch (SQLException e) {}
    }
}
