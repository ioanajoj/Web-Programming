<%@ page import="controller.DbConnection" %>
<%@ page import="model.Station" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: joj
  Date: 5/10/2019
  Time: 6:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DbConnection dbConnection = new DbConnection();

//    // Add current station to Route
//    Station currentStation = (Station) session.getAttribute("currentStation");
//    dbConnection.addStationToRoute(currentStation);
%>

<html>
<head>
    <title>End Trip</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <a href="index.jsp" class="navbar-brand"> Home </a>
</nav>

<div class="background" style="background-image: url('Cluj-Napoca_dark.png'); background-size: cover; width: 100%;
                                height: 40%; color: white; text-align: center; text-transform: uppercase;
                                text-shadow: 3px 2px grey; vertical-align: bottom; padding: 80px 0;">
    <h2>Thank you for completing a new trip!</h2>
    <h3> Here are the composing stations: </h3>
</div>

<br><br>

<div class="container" style="width: 80%; margin: auto">
    <%
        List<Station> route = dbConnection.getRoute();
        for(int i = 0; i < route.size(); i++) {
            out.print("<h6> " + i + ". ");
            out.print(route.get(i).getsName());
            out.print("</h6><br>");
        }
    %>
</div>

</body>
</html>
