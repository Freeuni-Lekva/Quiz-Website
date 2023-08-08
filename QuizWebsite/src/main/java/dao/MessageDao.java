package dao;

import models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    Connection conn;

    public MessageDao(Connection conn) {
        this.conn = conn;
    }

    public void addMessage(Message message) throws SQLException {
        PreparedStatement ps;
        ps = this.conn.prepareStatement("INSERT INTO messages(from_username, to_username, message, sent_date) VALUES(?,?,?,?)");
        ps.setString(1, message.getFromUsername());
        ps.setString(2, message.getToUsername());
        ps.setString(3, message.getMessage());
        ps.setTimestamp(4, message.getSentDate());
        ps.executeUpdate();
    }

    public List<Message> getMessagesOfUser(String username) throws SQLException {
        PreparedStatement ps;
        ps = this.conn.prepareStatement("SELECT * FROM messages WHERE (to_username = ?)");
        ps.setString(1, username);
        List<Message> messages = new ArrayList<Message>();
        ResultSet res = ps.executeQuery();
        while (res.next()) {
            String fromUsername = res.getString("from_username");
            String toUsername = res.getString("to_username");
            String message = res.getString("message");
            Timestamp sentDate = res.getTimestamp("sent_date");
            int messageId = res.getInt("id");
            Message msg = new Message(messageId, fromUsername, toUsername, message, sentDate);
            messages.add(msg);
        }

        return messages;
    }
}