<%--
  Created by IntelliJ IDEA.
  User: Alfonso
  Date: 5/30/2017
  Time: 4:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>About Us</title>
    <meta name="author" content="Alfonso Almazan Ruelas">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/more_details.css" rel="stylesheet">

    <title>Check Out</title>
</head>
<body>

    <jsp:include page="nav_bar.html" />

    <div>
        <h2>Confirmation ID: <%= request.getAttribute("confirmation_id")%> </h2>
        <h2> First Name: <%= request.getAttribute("first_name")%> </h2>
        <h2> Last Name: <%= request.getAttribute("last_name")%> </h2>
        <h2> Total Cost: <%= request.getAttribute("cost")%> </h2>
        <h2> Address: <%= request.getAttribute("address")%> <%= request.getAttribute("city")%> <%= request.getAttribute("state")%></h2>
        <h2> Order Submitted at: <%= request.getAttribute("time_submitted")%> </h2>
    </div>

    <jsp:include page="footer.html" />

</body>
</html>
