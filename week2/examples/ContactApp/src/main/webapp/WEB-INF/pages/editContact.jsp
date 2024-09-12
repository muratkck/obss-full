<%--
  Created by IntelliJ IDEA.
  User: murat
  Date: 31.07.2024
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit Contact</title>
</head>
<body>
<h1>Edit Contact</h1>
<form action="${pageContext.request.contextPath}/edit-contact" method="POST">

    <div>
        <label for="name">Name: </label>
        <input id="name" type="text" name="new-name" value="${contactById.name}">
    </div>
    <div>
        <label for="phone-number">Phone Number: </label>
        <input id="phone-number" type="text" name="new-phone-number" value="${contactById.phoneNumber}">
        <!-- security of hidden input=? -->
        <input type="hidden" name="contact-id" value="${contactById.id}" />
    </div>
    <input type="submit" value="Save">
</form>
</body>
</html>
