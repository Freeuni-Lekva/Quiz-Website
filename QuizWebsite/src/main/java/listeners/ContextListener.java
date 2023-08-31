package listeners;

import dao.*;
import models.History;

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
        FriendsDao friends = new FriendsDao(conn, "friends");
        MessageDao messages = new MessageDao(conn);
        FriendRequestDao friendRequests = new FriendRequestDao(conn);
        ChallengeDao challenges = new ChallengeDao(conn);
        HistoryDao historyDao = new HistoryDao(conn);
        AnswersDao answersDao = new AnswersDao(conn);
        QuizDao quizDao = new QuizDao(conn);
        QuestionsDao questionsDao = new QuestionsDao(conn, answersDao);
        AnnouncementsDao announcementsDao = new AnnouncementsDao(conn);
        servletContextEvent.getServletContext().setAttribute("users", users);
        servletContextEvent.getServletContext().setAttribute("friends", friends);
        servletContextEvent.getServletContext().setAttribute("messages", messages);
        servletContextEvent.getServletContext().setAttribute("friend_requests", friendRequests);
        servletContextEvent.getServletContext().setAttribute("challenges", challenges);
        servletContextEvent.getServletContext().setAttribute("history", historyDao);
        servletContextEvent.getServletContext().setAttribute("answersDao", answersDao);
        servletContextEvent.getServletContext().setAttribute("questionsDao", questionsDao);
        servletContextEvent.getServletContext().setAttribute("quizzes", quizDao);
        servletContextEvent.getServletContext().setAttribute("announcements", announcementsDao);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
