<%@ page import="model.Station" %>
<%@ page import="java.util.List" %>
<%@ page import="controller.DbConnection" %>
<%--
  Created by IntelliJ IDEA.
  User: joj
  Date: 5/8/2019
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DbConnection dbConnection = new DbConnection();

    // Change current station
    Station currentStation = (Station) session.getAttribute("currentStation");
    dbConnection.addStationToRoute(currentStation);
%>
<html>
<head>
    <title>Travel</title>

    <script type="text/javascript">
        function nextStation(stationID) {
            console.log("nextStation: " + stationID);
            var travelForm = document.getElementById("nextStationForm");
            var input = document.createElement("input");
            input.type = "hidden";
            input.name = "stationID";
            input.value = stationID;
            travelForm.appendChild(input);
            travelForm.submit();
        }
    </script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <a href="index.jsp" class="navbar-brand"> Home </a>
</nav>

<div class="background" style="background-image: url('Cluj-Napoca_dark.png'); background-size: cover; width: 100%;
                                height: 40%; color: white; text-align: center; text-transform: uppercase;
                                text-shadow: 3px 2px grey; vertical-align: bottom; padding: 80px 0;">
    <h2>Current station: </h2>
    <h2><%out.print(currentStation.getsName());%></h2>
</div>

<div class="container" style="width: 80%; margin: auto">

    <br><br>

    <h2>Next stations: </h2>
    <form action="changeCurrentStation.jsp" id="nextStationForm">
        <%
            List<Station> stations = dbConnection.getNextStations(currentStation);
            for(int i = 0; i < stations.size(); i++) {
                out.print(stations.get(i).getStationButton() + " | ");
            }
        %>
    </form>

    <h2>Your route until now: </h2>
    <%
        List<Station> route = dbConnection.getRoute();
        for(int i = 0; i < route.size(); i++) {
            out.print(route.get(i).getsName() + " ==> ");
        }
    %>

    <br>

    <div align="center" class="activator" >
        <%-- Go back one station--%>
        <a href="goBack.jsp">
            <button class='btn btn-danger' style="float: left; width: 50%"> Go back </button>
        </a>

        <%-- End trip --%>
        <a href="endTrip.jsp">
            <button class='btn btn-danger' style="width: 50%" onclick='nextStation(" + sid + ")'> End Trip </button>
        </a>
    </div>
</div>

</body>
</html>
