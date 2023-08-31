package servlets;

import dao.AnnouncementsDao;
import dao.UsersDao;
import models.Announcement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;


@WebServlet("/AddAnnouncementServlet")
public class AddAnnouncementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        AnnouncementsDao announcementsDao = (AnnouncementsDao) servletContext.getAttribute("announcements");

        // Get the submitted announcement text
        String newAnnouncementText = httpServletRequest.getParameter("newAnnouncement");

        // Create a new Announcement object
        Announcement newAnnouncement = new Announcement(new Date(), newAnnouncementText);

        // Store the announcement in the DAO
        try {
            announcementsDao.addAnnouncement(newAnnouncement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirect back to the announcements page
        httpServletRequest.getRequestDispatcher("/homepage.jsp").forward(httpServletRequest, response);
    }
}

