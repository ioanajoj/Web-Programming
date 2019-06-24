<%@ page import="controller.DbConnection" %>
<%@ page import="model.Station" %><%--
  Created by IntelliJ IDEA.
  User: joj
  Date: 5/10/2019
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DbConnection dbConnection = new DbConnection();

    // Get last station
    Station lastStation = dbConnection.getLastStation();

    // Remove last two stations
    dbConnection.removeLastTwoStationsInPath();

    // Set last station as current one
    session.setAttribute("currentStation", lastStation);

    // Refresh travel page
    response.sendRedirect("travel.jsp");
%>