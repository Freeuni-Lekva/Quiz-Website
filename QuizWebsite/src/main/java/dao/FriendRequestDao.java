package dao;

import models.FriendRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDao {
    Connection conn;

    public FriendRequestDao(Connection conn) {
        this.conn = conn;
    }

    public void createFriendRequest(FriendRequest req) {
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement("INSERT INTO friend_requests(from_username, to_username) VALUES(?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, req.getFromUsername());
            ps.setString(2, req.getToUsername());
            ps.executeUpdate();
            ResultSet set = ps.getGeneratedKeys();
            set.next();
            req.setId(set.getInt(1));
        } catch (SQLException e) {}
    }

    public void deleteFriendRequest(FriendRequest req) {
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement("DELETE FROM friend_requests WHERE from_username = ? AND to_username = ?");
            ps.setString(1, req.getFromUsername());
            ps.setString(2, req.getToUsername());
            ps.executeUpdate();
        } catch (SQLException e) {}
    }

    public List<FriendRequest> getFriendRequests(String username) {
        PreparedStatement ps;
        List<FriendRequest> requests = new ArrayList<FriendRequest>();
        try {
            ps = this.conn.prepareStatement("SELECT * FROM friend_requests WHERE to_username = ?");
            ps.setString(1, username);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int requestId = res.getInt("id");
                String fromUsername = res.getString("from_username");
                String toUsername = res.getString("to_username");
                FriendRequest req = new FriendRequest(requestId, fromUsername, toUsername);
                requests.add(req);
            }
        } catch (SQLException e) {}
        return requests;
    }
}
