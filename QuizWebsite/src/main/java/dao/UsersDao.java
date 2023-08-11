package dao;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    private Connection conn;


    public UsersDao(Connection conn) {
        this.conn = conn;
    }

    public UsersDao() {

    }


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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String username) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("user_type"), rs.getString("salt"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(int userId) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("user_type"), rs.getString("salt"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void removeUser(String username) {
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("DELETE FROM users WHERE username = ?");
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
