package test;

import dao.ChallengeDao;
import dao.FriendRequestDao;
import junit.framework.TestCase;
import models.FriendRequest;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestFriendRequests extends TestCase {
    private FriendRequestDao friendRequestDao;
    private void initDB() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/TestQuizWebsite");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        try {
            Connection connection = dataSource.getConnection();
            String dropTableSql = "DROP TABLE IF EXISTS friend_requests";
            String createTableSql = "CREATE TABLE IF NOT EXISTS friend_requests\n" +
                    "(\n" +
                    "    id            int primary key auto_increment,\n" +
                    "    from_username VARCHAR(100),\n" +
                    "    to_username   VARCHAR(100)\n" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableSql);
            statement.executeUpdate(createTableSql);
            friendRequestDao = new FriendRequestDao(dataSource.getConnection());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Test
    public void test() {
        initDB();
        FriendRequest request = new FriendRequest("nika", "jerry");
        friendRequestDao.createFriendRequest(request);
        FriendRequest req = friendRequestDao.getFriendRequests("jerry").get(0);
        assertTrue(req.getFromUsername().equals(request.getFromUsername()));
        assertTrue(req.getToUsername().equals(request.getToUsername()));
        friendRequestDao.deleteFriendRequest(req);
        assertEquals(0, friendRequestDao.getFriendRequests("giorgi").size());
        assertEquals(req.getId(), request.getId());
        assertNotNull(req.toString());
    }
}
