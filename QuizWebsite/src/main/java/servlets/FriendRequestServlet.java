package servlets;

import dao.FriendRequestDao;
import dao.UsersDao;
import models.FriendRequest;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/friendRequest")
public class FriendRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        UsersDao users = (UsersDao) httpServletRequest.getServletContext().getAttribute("users");
        ServletContext servletContext = httpServletRequest.getServletContext();
        FriendRequestDao friendRequests = (FriendRequestDao) servletContext.getAttribute("friend_requests");
        String userId = httpServletRequest.getParameter("username");
        User loggedUser = (User) httpServletRequest.getSession().getAttribute("loggedUser");
        String id = loggedUser.getUsername();

        // Create a new FriendRequest object
        FriendRequest newFriendRequest = new FriendRequest(id, userId);
        System.out.println(userId + id);
        // Call the createFriendRequest method and add the new friend request
        friendRequests.createFriendRequest(newFriendRequest);
        httpServletRequest.getRequestDispatcher("user?id="+users.getUser(userId).getId()).forward(httpServletRequest, httpServletResponse);

    }
}
