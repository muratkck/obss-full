<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="j_security_check" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="j_username"><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="j_password"><br><br>
    <input type="submit" value="Login">
</form>
<c:if test="${not empty param.error}">
    <p style="color:red;">Invalid username or password</p>
</c:if>
</body>
</html>
