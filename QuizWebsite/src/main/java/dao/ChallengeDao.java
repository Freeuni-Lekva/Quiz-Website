package dao;

import models.Challenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeDao {
    private Connection conn;

    public ChallengeDao(Connection conn) {
        this.conn = conn;
    }

    public void createChallenge(Challenge ch) throws SQLException {
        PreparedStatement ps;
        ps = this.conn.prepareStatement("INSERT INTO challenges(from_username, to_username, quiz_id) " +
                "VALUES(?, ?, ?)");
        ps.setString(1, ch.getFromUsername());
        ps.setString(2, ch.getToUsername());
        ps.setInt(3, ch.getQuizId());
        ps.executeUpdate();
    }

    public void deleteChallenge(Challenge ch) throws SQLException {
        PreparedStatement ps;
        ps = this.conn.prepareStatement("DELETE FROM challenges WHERE " +
                "from_username = ? AND to_username = ? AND quiz_id = ?");
        ps.setString(1, ch.getFromUsername());
        ps.setString(2, ch.getToUsername());
        ps.setInt(3, ch.getQuizId());
        ps.executeUpdate();
    }

    public List<Challenge> getChallenges(String username) throws SQLException {
        PreparedStatement ps;
        ps = this.conn.prepareStatement("SELECT * FROM challenges WHERE to_username = ?");
        ps.setString(1, username);
        ResultSet res = ps.executeQuery();
        List<Challenge> challenges = new ArrayList<Challenge>();
        while (res.next()) {
            int challengeId = res.getInt("id");
            String fromUsername = res.getString("from_username");
            String toUsername = res.getString("to_username");
            int quizId = res.getInt("quiz_id");
            Challenge ch = new Challenge(challengeId, fromUsername, toUsername, quizId);
            challenges.add(ch);
        }
        return challenges;
    }
}
