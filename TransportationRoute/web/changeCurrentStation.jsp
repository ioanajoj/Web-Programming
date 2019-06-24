<%@ page import="controller.DbConnection" %>
<%@ page import="model.Station" %><%--
  Created by IntelliJ IDEA.
  User: joj
  Date: 5/8/2019
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DbConnection dbConnection = new DbConnection();

//    // Add current station to Route
//    Station currentStation = (Station) session.getAttribute("currentStation");
//    dbConnection.addStationToRoute(currentStation);

    // Change current station to the one given by request
    String stationID = request.getParameter("stationID");
    session.setAttribute("currentStation", dbConnection.getStation(Integer.valueOf(stationID)));

    // Refresh page
    response.sendRedirect("travel.jsp");
%>
