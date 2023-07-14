<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 14.07.23
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Registration Unuccessful</h1>

<p>Sorry, the registration was unsuccessful.
    <% if (request.getParameter("username").isEmpty()) { %>
        username field was empty. fill it.
    <% } else if (request.getParameter("password").isEmpty()) { %>
        password field was empty. fill it.
    <% } else if (request.getParameter("select") == null) {%>
        select value of the user type
    <% } else {%>
    The username: <%= request.getParameter("username")%> you provided is already in use. Please try again with a different username.
    <% } %>
</p>
<a href="register.html">Try Again</a>
</body>
</html>
