package dao;

import models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDao {
    private Connection conn;


    public UsersDao(Connection conn) {
        this.conn = conn;
    }


    public void add(Account account) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO accounts (username, password, user_type) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, account.getUsername());
        ps.setString(2, account.getPassword());
        ps.setString(3, account.getUserType());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        long id = rs.getLong(1);
        user.setId(id);
    }

    public String getPassword(String username) {
        return null;
    }

    public Account getAccount(int uid) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM accounts WHERE id = ?");
        ps.setInt(1, uid);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
                rs.getString("user_type"));
    }
}
