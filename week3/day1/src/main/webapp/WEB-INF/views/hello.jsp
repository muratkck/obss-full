<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>

Welcome <%= request.getAttribute("username")%>

<ul>
<%
    for (Integer item: (List<Integer>) request.getAttribute("listOfItems")){
%>
    <li> <%= item%>
<%
    }
%>
</ul>

</body>
</html>