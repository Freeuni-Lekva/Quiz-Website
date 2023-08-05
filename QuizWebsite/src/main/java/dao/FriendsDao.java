package dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


public class FriendsDao {
    private final Connection conn;
    private final String table;

    public FriendsDao(Connection connection, String table) {
        this.conn = connection;
        this.table = table;
    }

    public void addFriend(int user_id, int friend_id) {
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO " + table + " (user_id, friend_id) " +
                            "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user_id);
            statement.setInt(2, friend_id);
            statement.executeUpdate();

            statement = conn.prepareStatement(
                    "INSERT INTO " + table + " (user_id, friend_id) " +
                            "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, friend_id);
            statement.setInt(2, user_id);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

    public List<Integer> getFriends(int user_id) {
        List<Integer> l = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT user_id, friend_id FROM " + table + " WHERE user_id = ?");
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) l.add(rs.getInt("friend_id"));

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return l;
    }

    public boolean areFriends(int first_id, int second_id){
        List<Integer> l = new ArrayList<>();
        try {
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT user_id, friend_id FROM " + table + " WHERE user_id = ? AND friend_id = ?");
            statement.setInt(1, first_id);
            statement.setInt(2, second_id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        return false;
    }

    public void removeFriend(int userId, int friendId) {
        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM " + table +
            " WHERE user_id = ? AND friend_id = ?");
            statement.setInt(1, userId);
            statement.setInt(2, friendId);
            statement.executeUpdate();

            statement = conn.prepareStatement("DELETE FROM " + table +
                    " WHERE user_id = ? AND friend_id = ?");
            statement.setInt(1, friendId);
            statement.setInt(2, userId);
            statement.executeUpdate();
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }
}
