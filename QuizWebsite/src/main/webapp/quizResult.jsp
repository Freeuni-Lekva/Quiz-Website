<%@ page import="models.Quiz" %>
<%@ page import="dao.HistoryDao" %>
<%@ page import="models.History" %>
<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UsersDao" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 8/22/2023
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>QuizResult</title>
</head>
<body>
<a href="homepage.jsp">Home</a>
<h1>Your final result:</h1>
<%
    Boolean failed = (Boolean) request.getAttribute("failed");
    if (failed) {
        out.println("<p>You failed the quiz. You couldn't make it in time.</p>");
    } else {
        String name = request.getAttribute("quizName").toString();
        Double sum = (Double) request.getAttribute("sum");
        Long duration = (Long) request.getAttribute("duration");
        out.println("<p>" + name + "</p");
        out.println("<p>Point: " + sum + "</p");
        out.println("<p>Duration: " + duration + " minutes</p");
    }
%>

<h1>Leaderboard:</h1>
<%
    ServletContext servletContext = request.getServletContext();
    HistoryDao historyDao = (HistoryDao) servletContext.getAttribute("history");
    UsersDao usersDao = (UsersDao) servletContext.getAttribute("users");
    User user = (User) request.getSession().getAttribute("loggedUser");
    List<History> historyList = historyDao.getHistory(user.getId());
    for (History history : historyList) { %>
<li>
    <%= usersDao.getUser(history.getUserId()).getUsername() %>
    <%= history.getGrade() %>
</li>
<%
    }
%>

</body>
</html>
