<%@ page import="models.Quiz" %>
<%@ page import="models.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="models.QuestionType" %>
<%@ page import="models.MultipleQuestion" %>
<%@ page import="dao.QuestionsDao" %>
<%@ page import="dao.AnswersDao" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 8/21/2023
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Quiz quiz = (Quiz) request.getAttribute("quiz");
    %>
    <title><%=quiz.getName()%></title>
</head>
<body>
    <a href="homepage.jsp">Home</a>
    <h1><%=quiz.getName()%></h1>
    <h2><%=quiz.getDescription()%></h2>
    <%
        QuestionsDao questionsDao = (QuestionsDao) request.getServletContext().getAttribute("questionsDao");
        AnswersDao answersDao = (AnswersDao) request.getServletContext().getAttribute("answersDao");
        List<Question> questions = questionsDao.getQuestions(quiz.getId());
        if (quiz.randomQuestions()) {
            Collections.shuffle(questions);
        }

        request.getSession().setAttribute("questions", questions);

        if (!quiz.multiplePages()) {
            out.println("<form action=\"ResultServlet\" method=\"post\">");
            int n = 1;
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);

                if (!question.getType().equals(QuestionType.PICTURE_QUESTION)) out.println("<p>" + (i + 1) + ") " + question.getQuestion() + "</p>");

                if (question.getType().equals(QuestionType.QUESTION_RESPONSE) || question.getType().equals(QuestionType.FILL_THE_BLANK)) {
                    out.println("<input type=\"text\" name=\"answer" + n + "\" placeholder=\"Your answer\">");
                    n++;
                } else if (question.getType().equals(QuestionType.MULTIPLE_CHOICE)) {
                    MultipleQuestion multipleQuestion = (MultipleQuestion) question;
                    List<String> choices = answersDao.getAnswers(question.getQuestionId());

                    for (String choice: choices) {
                        out.println("<input type=\"radio\" name=\"answer"+ i + "\" value=\"" + choice + "\">" + choice + "<br>");
                        n++;
                    }
                } else if (question.getType().equals(QuestionType.PICTURE_QUESTION)) {
                    out.println("<br>");
                    out.print("<p>" + (i + 1) + ") " + "</p>");
                    out.println("<img src=\"" + question.getQuestion() + "\" width=\"70\" height=\"70\">");
                    out.println("<br>");
                    out.println("<br>");
                    out.println("<input type=\"text\" name=\"answer" + n + "\" placeholder=\"Your answer\">");
                    n++;
                }
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Submit\">");
            out.println("<input type=\"hidden\" name=\"length\" value=\"" + questions.size() + "\">");
            out.println("</form>");
        }
    %>
</body>
</html>
