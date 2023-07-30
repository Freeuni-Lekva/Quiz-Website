<%@ page import="models.Account" %><%--
  Created by IntelliJ IDEA.
  User: nika
  Date: 30.07.23
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
</head>
<body>
    <h1>Hi this is your homepage</h1>
    <%
        Account user = (Account) request.getSession().getAttribute("loggedUser");
    %>
    <main>
        <section class="announcements">
        </section>

        <section class="pop-quizes">
        </section>

        <section class="new-quizes">
        </section>

        <!-- Should be list of activities about users recently taken quizes -->
        <section class="rec-quizes-act">
        </section>

        <!-- Should be list of activities about quizes which this users has created -->
        <section class="created-quizes-act">
        </section>

        <section class="achievements">
        </section>

        <!-- Should be list of friends recent activities i.e. taken quizes,
         achievemetns, etc. also here should be links to friends profiles and quizes
         -->
        <section class="friends-act">
        </section>
    </main>
    <aside>
<%--        <%--%>
<%--            MessageDao messageDao = (MessageDao) request.getServletContext().getAttribute("messages");--%>
<%--            List<Meesage> messages = messageDao.getMessages(user.getId());--%>
<%--            List<Message> requests = messages.stream().filter()--%>
<%--        %>--%>
        <div class="requests">

        </div>

        <div class="challenges">

        </div>

        <div class="notes">

        </div>
    </aside>
</body>
</html>
