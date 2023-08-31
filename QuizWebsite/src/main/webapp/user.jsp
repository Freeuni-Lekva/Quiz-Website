<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.FriendRequest" %>
<%@ page import="models.History" %>
<%@ page import="dao.*" %>

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
    User loggedUser = (User) request.getSession().getAttribute("loggedUser");
    int userId = loggedUser.getId();
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
            friendsList.add(users.getUser(fId));
            if(fId == userId) isFriend = true;
        }
        if(!isFriend) friendRequestSent = friendRequestDao.getFriendRequests(String.valueOf(userId)).contains(id);
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
    <a href="homepage.jsp">Home</a>
    <h1><%= user.getUsername() %></h1>
    <% if (!isFriend & !friendRequestSent) { %>
        <a href="friendRequest?username=<%= user.getUsername() %>">Send Friend Request</a>
    <% } %>
    <% if (isFriend) { %>
        <p><%= user.getUsername() %> is your friend</p>
    <% } %>
    <% if ("admin".equals(loggedUser.getUserType())) { %>
        <a href="removeUser?id=<%= user.getId() %>">Remove Account</a>
    <% } %>

    <h2>Friends</h2>
    <ul>
        <% for (User friend : friendsList) { %>
        <li>
            <a href="${pageContext.request.contextPath}/user?id=<%= friend.getId() %>"><%= friend.getUsername() %></a>
        </li>
        <% } %>
    </ul>
    <h2><%= user.getUsername() %>'s Activity</h2>
    <%
        ServletContext servletContext = request.getServletContext();
        HistoryDao historyDao = (HistoryDao) servletContext.getAttribute("history");
        QuizDao quizDao = (QuizDao) servletContext.getAttribute("quizzes");
        UsersDao usersDao = (UsersDao) servletContext.getAttribute("users");
        User users = (User) request.getSession().getAttribute("loggedUser");
        List<History> historyList = historyDao.getHistory(user.getId());
        for (History history : historyList) { %>
    <li>
        <%= quizDao.getQuiz(history.getQuizId()).getName() %>
        <%= history.getGrade() %>
    </li>
    <%
        }
    %>
    <br>
    <form action="ChallengeServlet" method="post">
        <h3>Challenge <%= user.getUsername() %></h3>
        <label for="quizId">Enter Quiz ID:</label>
        <input type="text" id="quizId" name="quizId">
        <input type="hidden" name="username" value="<%= user.getUsername() %>">
        <input type="submit" value="Challenge">
    </form>
</body>
</html>
