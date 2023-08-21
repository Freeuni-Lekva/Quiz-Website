package dao;

import models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    private Connection conn;

    public MessageDao(Connection conn) {
        this.conn = conn;
    }
    public void addMessage(Message message) {
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement("INSERT INTO messages(from_username, to_username, message, sent_date) VALUES(?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getFromUsername());
            ps.setString(2, message.getToUsername());
            ps.setString(3, message.getMessage());
            ps.setTimestamp(4, message.getSentDate());
            ps.executeUpdate();
            ResultSet set = ps.getGeneratedKeys();
            set.next();
            message.setMessageId(set.getInt(1));
        } catch (SQLException e) {}
    }

    public void deleteMessage(int messageId) {
        PreparedStatement ps;
        try {
            ps = this.conn.prepareStatement("DELETE FROM messages where id = ?");
            ps.setInt(1, messageId);
            ps.executeUpdate();
        } catch (SQLException e) {}
    }

    public List<Message> getMessagesOfUser(String username) {
        PreparedStatement ps;
        List<Message> messages = new ArrayList<Message>();
        try {
            ps = this.conn.prepareStatement("SELECT * FROM messages WHERE (to_username = ?)");
            ps.setString(1, username);
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
        } catch (SQLException e) {}

        return messages;
    }
}
