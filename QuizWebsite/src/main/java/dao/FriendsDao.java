package dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


/**
 * The FriendsDao class provides methods to interact with a database table
 * that stores information about friendships between users.
 */
public class FriendsDao {

    /**
     * The database connection to be used for executing queries.
     */
    private final Connection conn;

    /**
     * The name of the database table that stores friendship information.
     */
    private final String table;

    /**
     * Constructs a FriendsDao object with the given database connection
     * and table name.
     *
     * @param connection The database connection to be used for queries.
     * @param table      The name of the table storing friendship data.
     */
    public FriendsDao(Connection connection, String table) {
        this.conn = connection;
        this.table = table;
    }

    /**
     * Adds a new friendship between two users to the database.
     *
     * @param user_id   The ID of the first user.
     * @param friend_id The ID of the second user.
     */
    public void addFriend(int user_id, int friend_id) {
        try {
            // Prepare and execute SQL statements to insert the friendship into the table
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO " + table + " (user_id, friend_id) " +
                            "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user_id);
            statement.setInt(2, friend_id);
            statement.executeUpdate();

            // Insert the inverse friendship (friend_id, user_id) for bidirectional relationship
            statement.setInt(1, friend_id);
            statement.setInt(2, user_id);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Retrieves a list of friend IDs for a given user.
     *
     * @param user_id The ID of the user.
     * @return A list of friend IDs associated with the user.
     */
    public List<Integer> getFriends(int user_id) {
        List<Integer> friendList = new ArrayList<>();
        try {
            // Prepare and execute an SQL query to retrieve friend IDs for the given user
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT user_id, friend_id FROM " + table + " WHERE user_id = ?");
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                friendList.add(rs.getInt("friend_id"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return friendList;
    }

    /**
     * Checks if two users are friends.
     *
     * @param first_id  The ID of the first user.
     * @param second_id The ID of the second user.
     * @return True if the users are friends, false otherwise.
     */
    public boolean areFriends(int first_id, int second_id) {
        try {
            // Prepare and execute an SQL query to check if the given users are friends
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT user_id, friend_id FROM " + table + " WHERE user_id = ? AND friend_id = ?");
            statement.setInt(1, first_id);
            statement.setInt(2, second_id);
            ResultSet rs = statement.executeQuery();
            return rs.next(); // Return true if a row is found, indicating friendship
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    /**
     * Removes a friendship between two users from the database.
     *
     * @param userId   The ID of the first user.
     * @param friendId The ID of the second user.
     */
    public void removeFriend(int userId, int friendId) {
        try {
            // Prepare and execute SQL statements to delete the friendship from the table
            PreparedStatement statement = conn.prepareStatement("DELETE FROM " + table +
                    " WHERE user_id = ? AND friend_id = ?");
            statement.setInt(1, userId);
            statement.setInt(2, friendId);
            statement.executeUpdate();

            // Delete the inverse friendship (friend_id, user_id)
            statement.setInt(1, friendId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
