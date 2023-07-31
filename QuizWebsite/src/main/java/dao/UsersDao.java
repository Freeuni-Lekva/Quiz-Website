package dao;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao {
    private Connection conn;


    public UsersDao(Connection conn) {
        this.conn = conn;
    }


    public void add(User user) throws SQLException {
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
    }

    public String getPassword(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("password");
    }

    public User getAccount(int uid) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setInt(1, uid);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new User(rs.getString("username"), rs.getString("password"),
                rs.getString("user_type"));
    }
}
