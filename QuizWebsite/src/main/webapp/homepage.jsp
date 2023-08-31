<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dao.*" %>
<%@ page import="models.*" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 30.07.23
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
          integrity="sha512-NQ08jljSnyZMe+Z7uU42xv9wjCbPX7thzYHZPqZPt7LquKXX15i2PHfJ+ybtbV8MKLg6T+R3Tf1M/sEP5V8qA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <title>HomePage</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<%
    User user = (User) request.getSession().getAttribute("loggedUser");
    HistoryDao historyDao = (HistoryDao) request.getServletContext().getAttribute("history");
    List<History> historyList = historyDao.getHistory(user.getId());
    java.util.Calendar cal = java.util.Calendar.getInstance();
    int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
    AnnouncementsDao announcementsDao = (AnnouncementsDao) request.getServletContext().getAttribute("announcements");
    QuizDao quizDao = (QuizDao) request.getServletContext().getAttribute("quizzes");

    // Decide the greeting based on the current hour
    String greeting;
    if (hour >= 0 && hour < 12) {
        greeting = "Good morning";
    } else if (hour >= 12 && hour < 18) {
        greeting = "Good afternoon";
    } else {
        greeting = "Good evening";
    }
%>
<div id="username" style="display: none;">
    <%= user.getUsername() %>
</div>
<a href="logout">Logout</a>
<header class="search-container">
    <h1 class="greet-header"><%= greeting %> <%= user.getUsername() %>
    </h1>
    <nav>
        <div>
            <input type="text" id="search-input" placeholder="Search...">
            <button id="search-button">Search</button>
        </div>
        <div class="search-users">
            <ol id="search-users-list">
            </ol>
        </div>
    </nav>
</header>
<div class="flex-main">
    <main>
        <article class="announcements">
        </article>

        <article class="pop-quizes">
        </article>

        <article class="new-quizes">
        </article>

        <!-- Should be list of activities about users recently taken quizes -->
        <artilce class="rec-quizes-act">
        </artilce>

        <!-- Should be list of activities about quizes which this users has created -->
        <article class="created-quizes-act">
        </article>

        <artilce class="achievements">
        </artilce>

        <!-- Should be list of friends recent activities i.e. taken quizes,
         achievemetns, etc. also here should be links to friends profiles and quizes
         -->
        <article class="friends-act">
        </article>
        <article class="history">
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
                <% int size = Math.min(historyList.size(), 10);
                    for (int i = 0; i < size; i++) {
                        History history = historyList.get(i);
                %>
                <tr>
                    <td><%= history.getQuizId() %>
                    </td>
                    <td><%= history.getGrade() %>
                    </td>
                    <td><%= history.getDuration() %>
                    </td>
                    <td><a href="/QuizServlet?id=<%= history.getQuizId() %>">Do it again</a></td>
                </tr>
                <% } %>
            </table>
            <% } %>
            <a href="history.jsp">Click here to view history</a>
        </article>
        <div class="Announcements">
            <br>
            <h3>Announcements:</h3>
            <ul>
                <%
                    List<Announcement> announcements = null;
                    try {
                        announcements = announcementsDao.getAll();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    for (Announcement announcement : announcements) {
                %>
                <li>
                    <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(announcement.getDate()) %><br>
                    <%= announcement.getText() %>
                </li>
                <%
                    }
                    if ("admin".equals(user.getUserType())) {
                %>
                <form action="AddAnnouncementServlet" method="post">
                    <label>
                        <textarea name="newAnnouncement" rows="5" cols="40"></textarea>
                    </label><br>
                    <input type="submit" value="Add Announcement">
                </form>
                <%
                    }
                %>
            </ul>
        </div>
        <br>
        <div class="quizzes">
            <h3>Challenge Yourself: </h3>
            <ul>
                <%
                    List<Quiz> quizzes = null;
                    try {
                        quizzes = quizDao.getQuizzes();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    for (Quiz quiz : quizzes) {
                %>
                <li>
                    <p>
                        <a href="/QuizServlet?id=<%= quiz.getId() %>">
                            <%= quiz.getName() %> has Challenged you, click here to start
                        </a>
                    </p>
                </li>
                <%
                    }
                %>
            </ul>
        </div>

    </main>
    <aside>
        <%
            FriendRequestDao friendsReqs = (FriendRequestDao) request.getServletContext().getAttribute("friend_requests");
            MessageDao messageDao = (MessageDao) request.getServletContext().getAttribute("messages");
            ChallengeDao challengeDao = (ChallengeDao) request.getServletContext().getAttribute("challenges");
            List<FriendRequest> reqsList = friendsReqs.getFriendRequests(user.getUsername());
            List<Message> messages = messageDao.getMessagesOfUser(user.getUsername());
            List<Challenge> challenges = challengeDao.getChallenges(user.getUsername());
        %>
        <%
            String userType = user.getUserType();
            if ("admin".equals(userType)) {
        %>
        <div class="chat">
            <a class="nav-item nav-link"
               href="<%=request.getContextPath()%>/createQuizServlet?userId=<%=user.getId()%>">Create
                Quiz</a>
        </div>
        <%
            }
        %>
        <div class="requests">
            <h3>Friend Requests: </h3>
            <ul>
                <%
                    for (FriendRequest req : reqsList) {
                %>
                <li id="req-<%= req.getFromUsername() %>">
                    <p><%= req.getFromUsername() %>
                    </p>
                    <div>
                        <button class="acc-btn">Accept</button>
                        <button class="rej-btn">Reject</button>
                    </div>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
        <div class="challenges">
            <h3>Quiz Challenges: </h3>
            <ul>
                <%
                    for (Challenge challenge : challenges) {
                %>
                <li>
                    <p>
                        <a href="/QuizServlet?id=<%= challenge.getQuizId() %>">
                            <%= challenge.getFromUsername() %> has Challenged you, click here to start
                        </a>
                    </p>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
        <div class="notes">
            <h3>Notes: </h3>
            <ul>
                <%
                    for (Message message : messages) {
                %>
                <li>
                    <p><strong>From:</strong> <%= message.getFromUsername() %>
                    </p>
                    <p><strong>Message:</strong> <%= message.getMessage() %>
                    </p>
                    <p><strong>Sent at:</strong>
                        <%=
                        new SimpleDateFormat("HH:mm dd-MM-yyyy").format(message.getSentDate())
                        %>
                    </p>
                </li>
                <%
                    }
                %>
            </ul>
        </div>

        <div class="chat">
            <button id="chat-btn">
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                    <path d="M160 368c26.5 0 48 21.5 48 48v16l72.5-54.4c8.3-6.2 18.4-9.6 28.8-9.6H448c8.8 0 16-7.2 16-16V64c0-8.8-7.2-16-16-16H64c-8.8 0-16 7.2-16 16V352c0 8.8 7.2 16 16 16h96zm48 124l-.2 .2-5.1 3.8-17.1 12.8c-4.8 3.6-11.3 4.2-16.8 1.5s-8.8-8.2-8.8-14.3V474.7v-6.4V468v-4V416H112 64c-35.3 0-64-28.7-64-64V64C0 28.7 28.7 0 64 0H448c35.3 0 64 28.7 64 64V352c0 35.3-28.7 64-64 64H309.3L208 492z"/>
                </svg>
                Compose note
            </button>
            <form class="chat-form">
                <div class="remove-button-parent">
                    <button class="remove-btn">&#10006;</button>
                </div>
                <div class="form-group">
                    <label for="recipient">To:</label>
                    <input type="text" id="recipient" name="recipient" required>
                </div>
                <div class="form-group">
                    <label for="message">Message:</label>
                    <textarea id="message" name="message" rows="5" cols="40" required></textarea>
                </div>
                <button class="send-btn" type="submit">Send</button>
            </form>
        </div>
    </aside>
</div>
<script src="js/friends.js"></script>
<script src="js/search.js"></script>
<script src="js/message.js"></script>
</body>
</html>
