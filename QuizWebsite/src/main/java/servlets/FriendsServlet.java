package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FriendsDao;
import dao.FriendRequestDao;
import dao.UsersDao;
import models.FriendsHandler;
import models.FriendRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * FriendsServlet class is responsible for handling HTTP requests related to user friendship management.
 * It receives JSON data containing details about the friendship action to be performed (e.g., add or remove a friend).
 */
public class FriendsServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests (Not used in this implementation).
     * @param httpServletRequest The HTTP request object.
     * @param httpServletResponse The HTTP response object.
     * @throws ServletException If there's a servlet-related error.
     * @throws IOException If there's an I/O error while handling the request.
     */
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doGet(httpServletRequest, httpServletResponse);
    }

    /**
     * Handles HTTP POST requests related to friendship actions.
     * This method expects JSON data containing details about the friendship action.
     * It retrieves the necessary DAO objects and handles the friendship action using the FriendsHandler.
     * @param httpServletRequest The HTTP request object containing the JSON data.
     * @param httpServletResponse The HTTP response object.
     * @throws ServletException If there's a servlet-related error.
     * @throws IOException If there's an I/O error while handling the request.
     */
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        // Retrieve necessary DAO objects from the servlet context
        UsersDao usersDao = (UsersDao) httpServletRequest.getServletContext().getAttribute("users");
        FriendsDao friendsDao =  (FriendsDao) httpServletRequest.getServletContext().getAttribute("friends");
        FriendRequestDao friendsReqsDao =  (FriendRequestDao) httpServletRequest.getServletContext().getAttribute("friend_requests");

        // Read JSON data from the request
        BufferedReader reader = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        reader.close();

        // Convert JSON data to a Map object
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = mapper.readValue(jsonBuilder.toString(), Map.class);
        String fromUsername = (String) jsonData.get("from");
        String toUsername = (String) jsonData.get("to");
        boolean toAdd = Boolean.parseBoolean(httpServletRequest.getParameter("toAdd"));

        // Create a FriendsHandler instance and handle the friendship action
        FriendsHandler friendsHandler = new FriendsHandler(fromUsername, toUsername, usersDao, friendsDao, friendsReqsDao);
        friendsHandler.handle(toAdd);
    }
}
