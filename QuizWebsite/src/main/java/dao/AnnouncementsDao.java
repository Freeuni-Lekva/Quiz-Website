package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Announcement;

public class AnnouncementsDao {
    private Connection conn;
    public AnnouncementsDao(Connection conn) {
        this.conn = conn;
    }

    public void addAnnouncement(Announcement announcement) throws SQLException {
        String insertQuery = "INSERT INTO announcements (date, text) VALUES (?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
        preparedStatement.setTimestamp(1, new java.sql.Timestamp(announcement.getDate().getTime()));
        preparedStatement.setString(2, announcement.getText());
        preparedStatement.executeUpdate();
    }

    public List<Announcement> getAll() throws SQLException {
        List<Announcement> announcements = new ArrayList<>();
        String selectQuery = "SELECT * FROM announcements";
        PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Announcement announcement = new Announcement(
                    resultSet.getTimestamp("date"),
                    resultSet.getString("text")
            );
            announcements.add(announcement);
        }
        return announcements;
    }
}
