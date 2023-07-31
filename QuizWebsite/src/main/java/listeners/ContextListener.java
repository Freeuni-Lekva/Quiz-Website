package listeners;

import dao.ConnectionManager;
import dao.UsersDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
        }
        UsersDao users = new UsersDao(conn);
        servletContextEvent.getServletContext().setAttribute("users", users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
