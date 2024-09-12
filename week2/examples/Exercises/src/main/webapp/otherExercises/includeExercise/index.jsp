<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <title>Index</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>Welcome to My JSP Page</h1>
<%
    String name = "User";
    System.out.println("Hello, " + name + "!");
%>
<jsp:include page="myJSP.jsp" />
</body>
</html>
