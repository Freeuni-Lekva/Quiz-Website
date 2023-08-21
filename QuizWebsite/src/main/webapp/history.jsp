<%@ page import="dao.HistoryDao" %>
<%@ page import="models.History" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>History Summary</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
    HistoryDao historyDao = (HistoryDao) request.getServletContext().getAttribute("history");
    User user = (User) request.getSession().getAttribute("loggedUser");
    List<History> historyList = historyDao.getHistory(user.getId());
%>

<h1>Quiz History Summary</h1>

<% if (historyList.isEmpty()) { %>
<p>No quiz history available for this user.</p>
<% } else { %>
<table>
    <tr>
        <th>Quiz ID</th>
        <th>Grade</th>
        <th>Duration</th>
        <th>Try Again</th>
    </tr>
    <% for (History history : historyList) { %>
    <tr>
        <td><%= history.getQuizId() %></td>
        <td><%= history.getGrade() %></td>
        <td><%= history.getDuration() %></td>
        <td><a href="/quiz?quiz_id=<%= history.getQuizId() %>">Do it again</a></td>
    </tr>
    <% } %>
</table>
<% } %>
</body>
</html>
