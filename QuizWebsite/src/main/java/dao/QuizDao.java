package dao;

import models.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDao {
    private final Connection connection;

    public QuizDao(Connection connection) {
        this.connection = connection;
    }

    public Quiz getQuiz(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM quizzes WHERE quiz_id = ?");
        preparedStatement.setInt(1, id);
        ResultSet res = preparedStatement.executeQuery();
        res.next();
        return new Quiz(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getBoolean(5), res.getBoolean(6), res.getBoolean(7), res.getInt(8));
    }

    public List<Quiz> getQuizzes() throws SQLException {
        List<Quiz> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM quizzes");
        ResultSet res = preparedStatement.executeQuery();
        while (res.next()) {
            Quiz quiz = new Quiz(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getBoolean(5), res.getBoolean(6), res.getBoolean(7), res.getInt(8));
            list.add(quiz);
        }
        return list;
    }

    public void addQuiz(Quiz quiz) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO quizzes VALUES(?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, quiz.getId());
        preparedStatement.setString(2, quiz.getName());
        preparedStatement.setString(3, quiz.getDescription());
        preparedStatement.setInt(4, quiz.getDuration());
        preparedStatement.setBoolean(5, quiz.randomQuestions());
        preparedStatement.setBoolean(6, quiz.multiplePages());
        preparedStatement.setBoolean(7, quiz.immediateFeedback());
        preparedStatement.setInt(8, quiz.getAuthorId());
        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            quiz.setId(generatedId);
        } else {
            throw new SQLException("Failed to get generated ID for the quiz.");
        }
    }

    public void deleteQuiz(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM quizzes WHERE quiz_id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
