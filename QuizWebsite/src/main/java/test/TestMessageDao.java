package test;

import com.mysql.cj.protocol.x.XMessage;
import dao.MessageDao;
import dao.UsersDao;
import junit.framework.TestCase;
import models.Message;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMessageDao extends TestCase {
    private MessageDao messageDao;
    private void initDB() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/TestQuizWebsite");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        try {
            Connection connection = dataSource.getConnection();
            String dropTableSql = "DROP TABLE IF EXISTS messages";
            String createTableSql = "CREATE TABLE IF NOT EXISTS messages\n" +
                    "(\n" +
                    "    id            int primary key auto_increment,\n" +
                    "    from_username VARCHAR(100),\n" +
                    "    to_username   VARCHAR(100),\n" +
                    "    message       VARCHAR(1000) not null,\n" +
                    "    sent_date     timestamp     not null\n" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableSql);
            statement.executeUpdate(createTableSql);
            messageDao = new MessageDao(dataSource.getConnection());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Test
    public void testAdd() {
        initDB();
        Message message = new Message("giorgi", "nika", "hi", new Timestamp(System.currentTimeMillis()));
        messageDao.addMessage(message);
        Message toMessage = messageDao.getMessagesOfUser("nika").get(0);
        assertTrue(toMessage.getMessage().equals(message.getMessage()));
        assertTrue(toMessage.getToUsername().equals(message.getToUsername()));
        assertTrue(toMessage.getFromUsername().equals(message.getFromUsername()));
        assertEquals(toMessage.getMessageId(), message.getMessageId());
        messageDao.deleteMessage(message.getMessageId());
        assertEquals(0, messageDao.getMessagesOfUser("nika").size());
        assertNotNull(message.toString());
    }

}
