<%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 14.07.23
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>Registration Successful</h1>
  <p>Congratulations <%= request.getParameter("username") %>! Registration was successful</p>
  <a href="/login/login.jsp">Now login</a>
</body>
</html>
