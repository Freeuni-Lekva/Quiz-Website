package dao;

import models.PictureQuestion;
import models.Question;
import models.QuestionBuilder;
import models.QuestionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The QuestionsDao class provides methods for interacting with the database to retrieve and store
 * questions related to quizzes.
 */
public class QuestionsDao {

    private Connection connection;
    private AnswersDao answersDao;

    /**
     * Constructs a QuestionsDao instance with a database connection and an AnswersDao.
     *
     * @param connection The database connection to use for querying questions.
     * @param answersDao The AnswersDao instance for retrieving answers associated with questions.
     */
    public QuestionsDao(Connection connection, AnswersDao answersDao) {
        this.connection = connection;
        this.answersDao = answersDao;
    }

    /**
     * Retrieves a list of questions associated with a specific quiz.
     *
     * @param quizId The unique identifier of the quiz.
     * @return A list of Question objects related to the specified quiz.
     * @throws RuntimeException If a database error occurs during the query.
     */
    public List<Question> getQuestions(int quizId) {
        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM questions WHERE quiz_id = ?");
            statement.setInt(1, quizId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int questionId = resultSet.getInt("question_id");
                String questionContent = resultSet.getString("question_content");
                String questionType = resultSet.getString("question_type");
                String pictureUrl = resultSet.getString("picture_url");
                String answer = resultSet.getString("answer");
                questions.add(QuestionBuilder.create(answersDao, questionId, questionContent, questionType, pictureUrl, answer, quizId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }

    /**
     * Adds a new question to the database.
     *
     * @param question The Question object to be added.
     * @throws RuntimeException If a database error occurs during the insertion.
     */
    public void addQuestion(Question question) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO questions " +
                                    "(question_content, question_type, picture_url, quiz_id, answer) " +
                                    "VALUES (?, ?, ?, ?, ?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, question.getQuestion());
            statement.setString(2, question.getType());
            if (question.getType().equals(QuestionType.PICTURE_QUESTION)) {
                PictureQuestion pictureQuestion = (PictureQuestion) question;
                statement.setString(3, pictureQuestion.getImgUrl());
            } else {
                statement.setNull(3, java.sql.Types.NULL);
            }
            statement.setString(5, question.getAnswer());
            statement.setInt(4, question.getQuizId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int questionId = rs.getInt(1);
            question.setQuestionId(questionId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
