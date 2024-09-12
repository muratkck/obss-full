<%--
  Created by IntelliJ IDEA.
  User: murat
  Date: 31.07.2024
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    <c:out value="${message}"/>!
</h1>
<a href="${pageContext.request.contextPath}/">Return to Home Page</a>
</body>
</html>