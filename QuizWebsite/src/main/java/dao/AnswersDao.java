package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The AnswersDao class provides methods for retrieving and storing answers related to questions.
 */
public class AnswersDao {

    private Connection connection;

    /**
     * Constructs an AnswersDao instance with a database connection.
     *
     * @param connection The database connection to use for querying and storing answers.
     */
    public AnswersDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves a list of answers associated with a specific question.
     *
     * @param questionId The unique identifier of the question.
     * @return A list of String objects representing the answers.
     * @throws RuntimeException If a database error occurs during the query.
     */
    public List<String> getAnswers(int questionId) {
        List<String> answers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM answers WHERE question_id = ?");
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answers.add(resultSet.getString("answer"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    /**
     * Adds one or more answers to a specific question in the database.
     *
     * @param questionId The unique identifier of the question.
     * @param answers The answers to be added.
     * @throws RuntimeException If a database error occurs during insertion.
     */
    public void addAnswers(int questionId, String... answers) {
        for (String answer : answers) {
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO answers " +
                        "(answer, question_id) " + "VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setString(1, answer);
                statement.setInt(2, questionId);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
