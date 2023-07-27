package listeners;

import dao.UsersDao;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicDataSource dataSource = new BasicDataSource();
        UsersDao users = new UsersDao(dataSource);
        servletContextEvent.getServletContext().setAttribute("users", users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
