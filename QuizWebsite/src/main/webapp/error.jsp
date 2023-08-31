<%--
  Created by IntelliJ IDEA.
  User: www.leptopi.ge
  Date: 31.07.2023
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><% request.getAttribute("errorMessage"); %></title>
</head>
<body>
  <a href="${pageContext.request.contextPath}/home">QuizBug</a>
  <h1><% request.getAttribute("errorMessage"); %></h1>
</body>
</html>
