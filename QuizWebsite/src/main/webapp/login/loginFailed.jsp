<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 7/17/2023
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>LoginFailed</title>
</head>
<body>
<h1>Username or password is incorrect!</h1>
<p>Please try again:</p>
<form action="/login" method="post">
  <input type="text" name="username" placeholder="Username">
  <br>
  <br>
  <input type="password" name="password" placeholder="Password">
  <br>
  <br>
  <input type="submit" value="Log In">
</form>
<a href="/register/register.html">Create new account</a>
</body>
</html>
