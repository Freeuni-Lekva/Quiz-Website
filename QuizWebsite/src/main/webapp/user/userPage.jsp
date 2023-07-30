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
    if(id == null){

    }else{
        UsersDao users = (UsersDao) application.getAttribute("users");
        FriendsDao friends = (FriendsDao) application.getAttribute("friends");
        user = users.getUser(id);
        List<Integer> friendsId = friends.getFriends(Integer.parseInt(id));
        for(int fId : friendsId) friendsList.add(users.getUser(String.valueOf(fId)));
    }
%>
<html>
<head>
    <title>
        <%= user.getUserName() %>
    </title>
</head>
<body>
    <h1><%= user.getUserName() %>!</h1>
    <p>Friends:</p>
    <ul>
        <% for (User friend : friendsList) { %>
        <li>
            <a href="${pageContext.request.contextPath}/user?id=<%= friend.getId() %>"><%= friend.getUserName() %></a>
        </li>
        <% } %>
    </ul>
</body>
</html>
