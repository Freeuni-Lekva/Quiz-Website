<%@ page import="dao.UsersDao" %>
<%@ page import="models.User" %>
<%@ page import="dao.FriendsDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: www.leptopi.ge
  Date: 30.07.2023
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = null;
    List<User> friendsList = new ArrayList<>();
    String id = request.getParameter("id");
    String userId = (String) request.getAttribute("loggedUser");
    FriendRequestsDao friendRequestsDao;
    boolean isFriend = false;
    boolean friendRequestSent = false;
    if(id != null){
        UsersDao users = (UsersDao) application.getAttribute("users");
        FriendsDao friends = (FriendsDao) application.getAttribute("friends");
        friendRequestDao = (FriendRequestsDao) application.getAttribute("friendRequest");
        user = users.getUser(id);
        List<Integer> friendsId = friends.getFriends(Integer.parseInt(id));
        for(int fId : friendsId){
            friendsList.add(users.getUser(String.valueOf(fId)));
            if(fId == Integer.parseInt(userId)) isFriend = true;
        }
        if(!isFriend) friendRequestSent = friendRequestDao.getFriendRequests(userId).contains(id);
    }else{
        request.setAttribute("errorMessage", "Error: User not found!");
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
%>
<html>
<head>
    <title>
        <%= user.getUserName() %>
    </title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/home">QuizBug</a>
    <h1><%= user.getUserName() %>!</h1>
    <% if (!isFriend & !friendRequestSent) { %>
        <button id="sendFriendRequestBtn" onclick="sendFriendRequest()">Send Friend Request</button>
    <% } %>
    <% if (isFriend) { %>
        <p><%= user.getUserName() %> is your friend</p>
    <% } %>
    <script>
        function sendFriendRequest() {
            // Perform the AJAX request
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4) {
                    if (xhr.status === 200) {
                        // Request was successful
                        var response = JSON.parse(xhr.responseText);
                        if (response.success) {
                            alert("Friend request sent successfully!");
                            <%= (FriendRequestsDao)(application.getAttribute("friendRequests")).addFriendRequest(
                                    new FriendsRequest(userId id)) %>
                        } else {
                            alert("Error sending friend request: " + response.message);
                        }
                    } else {
                        // Request failed
                        alert("An error occurred while sending the friend request.");
                    }
                }
            };

            var url = "user?id=" + <%= application.getAttribute("user") %>;

            xhr.open("POST", url, true);
            xhr.send(null);
        }
    </script>
    <h2>Friends</h2>
    <ul>
        <% for (User friend : friendsList) { %>
        <li>
            <a href="${pageContext.request.contextPath}/user?id=<%= friend.getId() %>"><%= friend.getUserName() %></a>
        </li>
        <% } %>
    </ul>
    <h2><%= user.getUserName() %>'s Activity</h2>
</body>
</html>
