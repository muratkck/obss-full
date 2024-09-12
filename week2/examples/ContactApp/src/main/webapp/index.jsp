<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact APP</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/create-contact">
        <input value="Create Contact" type="submit" />
    </form>
    <form action="${pageContext.request.contextPath}/search-contact">
        <input value="Search Contact" type="submit"/>
    </form>

</body>
</html>