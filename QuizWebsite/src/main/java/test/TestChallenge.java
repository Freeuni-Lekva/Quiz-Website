package test;

import dao.ChallengeDao;
import dao.MessageDao;
import junit.framework.TestCase;
import models.Challenge;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestChallenge extends TestCase {
    private ChallengeDao challengeDao;
    private void initDB() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/TestQuizWebsite");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        try {
            Connection connection = dataSource.getConnection();
            String dropTableSql = "DROP TABLE IF EXISTS challenges";
            String createTableSql = "CREATE TABLE IF NOT EXISTS challenges\n" +
                    "(\n" +
                    "    id            int primary key auto_increment,\n" +
                    "    from_username VARCHAR(100),\n" +
                    "    to_username   VARCHAR(100),\n" +
                    "    quiz_id       VARCHAR(1000) not null\n" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableSql);
            statement.executeUpdate(createTableSql);
            challengeDao = new ChallengeDao(dataSource.getConnection());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Test
    public void test() {
        initDB();
        Challenge challenge = new Challenge("tom", "jerry", 1);
        challengeDao.createChallenge(challenge);
        Challenge ch = challengeDao.getChallenges("jerry").get(0);
        assertTrue(challenge.getFromUsername().equals(ch.getFromUsername()));
        assertTrue(challenge.getToUsername().equals(ch.getToUsername()));
        assertNotNull(ch.toString());
        assertEquals(challenge.getChallengeId(), ch.getChallengeId());
        assertEquals(challenge.getQuizId(), ch.getQuizId());
        challengeDao.deleteChallenge(challenge);
        assertEquals(0, challengeDao.getChallenges("jerry").size());
    }
}
