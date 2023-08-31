<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Question Management</title>
</head>
<body>
<a href="homepage.jsp">Home</a>
<h1>Quiz Question Management</h1>

<form method="post" action="addQuizServlet">
    <% int numQuestions = Integer.parseInt(request.getParameter("numQuestions")); %>
    <% String quizName = request.getParameter("quizName");
        request.getSession().setAttribute("quizName", quizName);
    %>

    <% for (int i = 1; i <= numQuestions; i++) { %>
    <h3>Question <%= i %>
    </h3>

    <label for="questionText<%= i %>">Question Text:</label>
    <input type="text" id="questionText<%= i %>" name="questionText<%= i %>" required><br>

    <label for="questionType<%= i %>">Question Type:</label>
    <select id="questionType<%= i %>" name="questionType<%= i %>" required>
        <option value="multiple_choice">Multiple Choice</option>
<%--        <option value="fill_the_blank">Fill the Blank</option>--%>
        <option value="picture_question">Picture Question</option>
<%--        <option value="request_response">Request Response</option>--%>
    </select><br>

    <label for="answer1<%= i %>">Answer 1:</label>
    <input type="text" id="answer1<%= i %>" name="answer1<%= i %>" required><br>

    <label for="answer2<%= i %>">Answer 2:</label>
    <input type="text" id="answer2<%= i %>" name="answer2<%= i %>" required><br>

    <label for="answer3<%= i %>">Answer 3:</label>
    <input type="text" id="answer3<%= i %>" name="answer3<%= i %>" required><br>

    <label for="answer4<%= i %>">Answer 4:</label>
    <input type="text" id="answer4<%= i %>" name="answer4<%= i %>" required><br>

    <label for="correctAnswer<%= i %>">Correct Answer:</label>
    <input type="radio" id="correctAnswer<%= i %>_1" name="correctAnswer<%= i %>" value="1" required>
    <label for="correctAnswer<%= i %>_1">Answer 1</label>
    <input type="radio" id="correctAnswer<%= i %>_2" name="correctAnswer<%= i %>" value="2">
    <label for="correctAnswer<%= i %>_2">Answer 2</label>
    <input type="radio" id="correctAnswer<%= i %>_3" name="correctAnswer<%= i %>" value="3">
    <label for="correctAnswer<%= i %>_3">Answer 3</label>
    <input type="radio" id="correctAnswer<%= i %>_4" name="correctAnswer<%= i %>" value="4">
    <label for="correctAnswer<%= i %>_4">Answer 4</label><br><br>
    <% } %>

    <input type="submit" value="Submit">
</form>

</body>
</html>
