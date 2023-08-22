package test;

import dao.HistoryDao;
import dao.UsersDao;
import junit.framework.TestCase;
import models.User;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUsersDao extends TestCase {
    private UsersDao usersDao;
    private void initDB() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/TestQuizWebsite");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        try {
            Connection connection = dataSource.getConnection();
            String dropTableSql = "DROP TABLE IF EXISTS users";
            String createTableSql = "CREATE TABLE IF NOT EXISTS users\n" +
                    "(\n" +
                    "    user_id   INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    username  VARCHAR(100) UNIQUE,\n" +
                    "    password  VARCHAR(100),\n" +
                    "    user_type VARCHAR(100),\n" +
                    "    salt      VARCHAR(100)\n" +
                    "    );";
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableSql);
            statement.executeUpdate(createTableSql);
            usersDao = new UsersDao(dataSource.getConnection());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Test
    public void testAddAndGet() {
        initDB();
        User user = new User("giorgi", "giorgi123", "user", "123");
        usersDao.add(user);
        User user1 = usersDao.getUser("giorgi");
        assertTrue(user.getUserType().equals(user1.getUserType()));
        assertTrue(user.getSalt().equals(user1.getSalt()));
        assertTrue(user.getUsername().equals(user1.getUsername()));
        assertTrue(user.getPassword().equals(user1.getPassword()));
        User user2 = usersDao.getUser(1);
        assertTrue(user.getUserType().equals(user2.getUserType()));
        assertTrue(user.getSalt().equals(user2.getSalt()));
        assertTrue(user.getUsername().equals(user2.getUsername()));
        assertTrue(user.getPassword().equals(user2.getPassword()));
        assertEquals(user.getId(), user2.getId());
        usersDao.removeUser("giorgi");
        assertNull(usersDao.getUser("giorgi"));
    }

    @Test
    public void testSearch() {
        initDB();
        UsersDao mock = new UsersDao();
        usersDao.add(new User("giorgi", "123", "admin"));
        usersDao.add(new User("george", "456", "user"));
        usersDao.add(new User("gustavo", "1231", "user"));
        assertEquals(3, usersDao.searchPrefix("g").size());
        assertEquals(1, usersDao.searchPrefix("gio").size());
        usersDao.removeUser("george");
        assertEquals(2, usersDao.searchPrefix("g").size());
        usersDao.removeUser("gustavo");
        assertEquals(1, usersDao.searchPrefix("g").size());
        usersDao.removeUser("giorgi");
        assertEquals(0, usersDao.searchPrefix("g").size());
        assertEquals(0, usersDao.searchPrefix("").size());
        assertNull(usersDao.getUser("asdadsas"));
        assertNull(usersDao.getUser(-1));
    }
}
