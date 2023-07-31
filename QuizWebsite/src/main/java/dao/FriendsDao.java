package dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


public class FriendsDao {
    private final DataSource ds;

    public FriendsDao(DataSource ds) {
        this.ds = ds;
    }

    public void addFriend(int user_id, int friend_id) {
        Connection conn = null;
        try {
            conn = ds.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO friends (user_id, friend_id) " +
                            "VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user_id);
            statement.setInt(2, friend_id);
            statement.executeUpdate();

            statement = conn.prepareStatement(
                    "INSERT INTO friends (user_id, friend_id) " +
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
        Connection conn = null;
        List<Integer> l = new ArrayList<>();
        try {
            conn = ds.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT user_id, friend_id FROM friends WHERE user_id = ?");
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
        Connection conn = null;
        List<Integer> l = new ArrayList<>();
        try {
            conn = ds.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT user_id, friend_id FROM friends WHERE user_id = ? AND friend_id = ?");
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
}
