package listeners;

import dao.UsersDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UsersDao users = new UsersDao();
        servletContextEvent.getServletContext().setAttribute("users", users);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
