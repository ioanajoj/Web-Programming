<%@ page import="controller.DbConnection" %>
<%@ page import="model.Station" %>
<%--
  Created by IntelliJ IDEA.
  User: joj
  Date: 5/8/2019
  Time: 11:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DbConnection dbConnection = new DbConnection();

    // Create Route table in DB
    dbConnection.createRoute();

    // Get first station from request
    String stationID = request.getParameter("startingStation");
    Station startStation = dbConnection.getStation(Integer.valueOf(stationID));

    // Set currentStation attribute
    session.setAttribute("currentStation", startStation);

    // Go to travel page
    response.sendRedirect("travel.jsp");
%>