<%--
  Created by IntelliJ IDEA.
  User: murat
  Date: 31.07.2024
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Contact</title>
</head>
<body>
    <h1>Create a Contact</h1>
    <form action="${pageContext.request.contextPath}/create-contact" method="POST">
<%--        <div>--%>
<%--            <label for="id">id: </label>--%>
<%--            <input id="id" type="text" name="id">--%>
<%--        </div>--%>
        <div>
            <label for="name">Name: </label>
            <input id="name" type="text" name="name">
        </div>
        <div>
        <label for="phone-number">Phone Number: </label>
        <input id="phone-number" type="text" name="phone_number">
        </div>
        <input type="submit" value="Add Contact">
    </form>
</body>
</html>
