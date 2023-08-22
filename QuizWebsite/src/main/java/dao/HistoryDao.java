package dao;

import models.History;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao {
    private Connection connection;

    /**
     * Constructs a HistoryDao with the provided database connection.
     * @param connection The database connection to use for database operations.
     */
    public HistoryDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a list of history records associated with a given user ID.
     * @param userId The ID of the user whose history records are to be retrieved.
     * @return A list of History objects representing the user's history records.
     * @throws RuntimeException If a database error occurs during the operation.
     */
    public List<History> getHistory(int userId) {
        List<History> histories = new ArrayList<>();
        try {
            PreparedStatement statement
                    = connection.prepareStatement("SELECT * FROM history WHERE user_id = ?");
            statement.setInt(1, userId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int historyId = set.getInt("history_id");
                int quizId = set.getInt("quiz_id");
                double grade = set.getDouble("grade");
                Time duration = set.getTime("duration");
                histories.add(new History(historyId, userId, quizId, grade, duration));
            }
        } catch (SQLException e) {}
        return histories;
    }

    /**
     * Adds a new history record to the database.
     * @param history The History object representing the history record to be added.
     * @throws RuntimeException If a database error occurs during the operation.
     */
    public void addHistory(History history) {
        try {
            PreparedStatement statement
                    = connection.prepareStatement("INSERT INTO history(user_id, quiz_id, grade, duration) VALUES(?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, history.getUserId());
            statement.setInt(2, history.getQuizId());
            statement.setDouble(3, history.getGrade());
            statement.setTime(4, history.getDuration());
            statement.executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            set.next();
            history.setHistoryId(set.getInt(1));
        } catch (SQLException e) {}
    }
}
