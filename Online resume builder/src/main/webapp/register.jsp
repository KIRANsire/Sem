<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h2>User Registration Form</h2>
    <form action="register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required /><br /><br />
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required /><br /><br />
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required /><br /><br />
        <input type="submit" value="Register" />
    </form>
</body>
</html>
