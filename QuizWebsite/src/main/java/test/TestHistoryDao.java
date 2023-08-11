package test;

import dao.HistoryDao;
import junit.framework.TestCase;
import models.History;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

public class TestHistoryDao extends TestCase {
    private HistoryDao historyDao;

    public void initDB() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/TestQuizWebsite");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        try {
            Connection connection = dataSource.getConnection();
            String dropTableSql = "DROP TABLE IF EXISTS history";
            String createTableSql = "CREATE TABLE IF NOT EXISTS history (\n" +
                    "    history_id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    user_id INT,\n" +
                    "    quiz_id INT,\n" +
                    "    grade DECIMAL(5, 2),\n" +
                    "    duration TIME\n" +
                    ");";
            Statement statement = connection.createStatement();
            statement.executeUpdate(dropTableSql);
            statement.executeUpdate(createTableSql);
            historyDao = new HistoryDao(dataSource.getConnection());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    @Test
    public void testAdd() {
        initDB();
        History history = new History(1, 1, 56, new Time(100000));
        historyDao.addHistory(history);
        List<History> histories = historyDao.getHistory(1);
        assertEquals(histories.get(0).getHistoryId(), history.getHistoryId());
        assertEquals(histories.get(0).getGrade(), history.getGrade());
        assertEquals(histories.get(0).getQuizId(), history.getQuizId());
        assertEquals(histories.get(0).getDuration(), history.getDuration());
        history.setHistoryId(1);
        assertEquals(histories.get(0).getHistoryId(), history.getHistoryId());
    }

    public void testAddMore() {
        initDB();
        History history = new History(1, 2, 24, new Time(200000));
        historyDao.addHistory(history);
        history = new History(1, 2, 58, new Time(130000));
        historyDao.addHistory(history);
        history = new History(1, 3, 67, new Time(170000));
        historyDao.addHistory(history);
        history = new History(2, 3, 87, new Time(140000));
        historyDao.addHistory(history);
        List<History> historyOfOne = historyDao.getHistory(1);
        assertEquals(3, historyOfOne.size());
        assertEquals(1, historyDao.getHistory(2).size());
    }
}
