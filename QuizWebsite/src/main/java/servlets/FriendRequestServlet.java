package servlets;

import dao.FriendRequestDao;
import dao.UsersDao;
import models.FriendRequest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FriendRequestServlet", value = "/FriendRequestServlet")
public class FriendRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        ServletContext servletContext = httpServletRequest.getServletContext();
        FriendRequestDao friendRequests = (FriendRequestDao) servletContext.getAttribute("friend_requests");
        String userId = httpServletRequest.getParameter("id");
        String id = httpServletRequest.getParameter("secId");


        // Create a new FriendRequest object
        FriendRequest newFriendRequest = new FriendRequest(userId, id);
        System.out.println(userId + id);
        // Call the createFriendRequest method and add the new friend request
        friendRequests.createFriendRequest(newFriendRequest);
        httpServletRequest.getRequestDispatcher("user?id="+userId).forward(httpServletRequest, httpServletResponse);

    }
}
