<%@ page import="dao.UsersDao" %>
<%@ page import="models.User" %>
<%@ page import="dao.FriendsDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.FriendRequestDao" %>
<%@ page import="models.FriendRequest" %>

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
    List<User> friendsList = new ArrayList<User>();
    String id = request.getParameter("id");
    String userId = (String) request.getAttribute("loggedUser");
    FriendRequestDao friendRequestDao;
    boolean isFriend = false;
    boolean friendRequestSent = false;
    if(id != null){
        UsersDao users = (UsersDao) application.getAttribute("users");
        FriendsDao friends = (FriendsDao) application.getAttribute("friends");
        friendRequestDao = (FriendRequestDao) application.getAttribute("friend_requests");
        user = users.getUser(Integer.parseInt(id));
        List<Integer> friendsId = friends.getFriends(Integer.parseInt(id));
        for(int fId : friendsId){
            friendsList.add(users.getUser(String.valueOf(fId)));
            if(fId == Integer.parseInt(userId)) isFriend = true;
        }
        if(!isFriend) friendRequestSent = friendRequestDao.getFriendRequests(userId).contains(id);
    }else{
        request.setAttribute("errorMessage", "Error: User not found!");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
%>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1, h2 {
            margin: 10px 0;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-bottom: 5px;
        }

        button {
            background-color: #0056b3;
            color: white;
            border: none;
            padding: 6px 10px;
            cursor: pointer;
        }

        button:hover {
            background-color: #2980b9;
        }

        p {
            font-style: italic;
        }
    </style>
    <title>
        <%= user.getUsername() %>
    </title>
</head>
<body>
    <h1><%= user.getUsername() %></h1>
    <% if (!isFriend & !friendRequestSent) { %>
        <button id="sendFriendRequestBtn" onclick="sendFriendRequest()">Send Friend Request</button>
    <% } %>
    <% if (isFriend) { %>
        <p><%= user.getUsername() %> is your friend</p>
    <% } %>
    <% if ("admin".equals(user.getUserType())) { %>
        <button id="removeAccountBtn" onclick="removeAccount()">Remove Account</button>
    <% } %>
    <button id="removeAccountBtn" onclick="removeAccount()">Remove Account</button>

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
                        } else {
                            alert("Error sending friend request: " + response.message);
                        }
                    } else {
                        // Request failed
                        alert("An error occurred while sending the friend request " + xhr.status);
                    }
                }
            };
            var url = "friendRequestServlet?id=" + <%= userId %> + "&secId=" + <%= id %>;

            xhr.open("POST", url, true);
            xhr.send(null);
        }


        function removeAccount() {
            var confirmRemove = confirm("Are you sure you want to remove this account?");
            if (confirmRemove) {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            // Request was successful
                            var response = JSON.parse(xhr.responseText);
                            if (response.success) {
                                alert("Account removed successfully!");
                            } else {
                                alert("Error removing account: " + response.message);
                            }
                        } else {
                            // Request failed
                            alert("An error occurred while removing the account " + xhr.status);
                        }
                    }
                };

                var url = "removeUserServlet?id=" + <%= id %>;

                xhr.open("POST", url, true);
                xhr.send(null);
            }
        }
    </script>
    <h2>Friends</h2>
    <ul>
        <% for (User friend : friendsList) { %>
        <li>
            <a href="${pageContext.request.contextPath}/user?id=<%= friend.getId() %>"><%= friend.getUsername() %></a>
        </li>
        <% } %>
    </ul>
    <h2><%= user.getUsername() %>'s Activity</h2>
</body>
</html>
