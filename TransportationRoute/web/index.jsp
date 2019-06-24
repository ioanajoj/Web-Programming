<%@ page import="model.Station" %>
<%@ page import="controller.DbConnection" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: joj
  Date: 5/8/2019
  Time: 9:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    DbConnection dbConnection = new DbConnection();
%>
<html>
<head>
    <title>Start Journey</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <a href="index.jsp" class="navbar-brand"> Home </a>
</nav>

<div class="background" style="background-image: url('Cluj-Napoca_dark.png'); background-size: cover; width: 100%;
                                height: 40%; color: white; text-align: center; text-transform: uppercase;
                                text-shadow: 3px 2px grey; vertical-align: bottom; padding: 80px 0;">
    <h2>Choose a starting station:</h2>
</div>
<br><br>
<div class="container" style="width: 80%; margin: auto">
    <form action="startRoute.jsp">
        <label for="startingStation"></label>
        <select id="startingStation" name="startingStation" class="form-control">
            <option selected>Choose...</option>
            <%
                List<Station> stations = dbConnection.getAllStations();
                for(int i = 0; i<stations.size(); i++) {
                    out.print(stations.get(i).getStationOption());
                }
            %>
        </select>
        <br>
        <button type="submit" class="btn btn-success" style="width: 100%"> Start Journey </button>
    </form>
</div>

</body>
</html>
