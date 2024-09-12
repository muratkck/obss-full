<%--
  Created by IntelliJ IDEA.
  User: murat
  Date: 31.07.2024
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <style>
        .tab-space {
            display: inline-block;
            width: 8em; /* Adjust the width as needed */
        }
        .contact-row {
            display: flex;
            align-items: center; /* This ensures vertical centering */
        }
        .contact-row form {
            margin-left: 1em; /* Adjust the margin as needed */
        }
        form {
            display: inline-block;
            text-align: center;
        }
    </style>
</head>
<body>
<h1>Search Contacts</h1>
<form action="${pageContext.request.contextPath}/search-contact" method="POST">
    <input type="text" name="contact-name" placeholder="Enter contact name" />
    <input type="submit" value="Search" />
</form>
<c:forEach items="${contactList}" var="contact">
    <div class="contact-row">
        <span class="tab-space"><c:out value="${contact.name}"/></span>
        <c:out value="${contact.phoneNumber}"/>
        <form action="${pageContext.request.contextPath}/edit-contact">
            <!-- security of hidden input=? -->
            <input type="hidden" name="contact-id" value="${contact.id}" />
            <input type="submit" value="Edit">
        </form>
    </div>
    <br/>
</c:forEach>
</body>
</html>
