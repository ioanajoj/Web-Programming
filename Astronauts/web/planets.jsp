<%--
  Created by IntelliJ IDEA.
  User: joj
  Date: 6/24/2019
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Planets</title>
    <style>
        button {
            float: right;
        }
        .planetBase {
            position: absolute;
            opacity: 1;
            width: 200px;
            height: 200px;
            box-shadow: 3px 3px 5px gray;
            border-radius: 50%;
        }
        .popup {
            position: absolute;;
            width: 200px;
            height: 100px;
            color: black;
        }
    </style>
    <script src="js/jquery-2.0.3.js"></script>
    <script language="JavaScript">
        var mouseX, mouseY;
        var planets = [], planet_index = 0;

        function planetToString(planet) {
            return "Planet: " + planet.name;
        }

        function showInfoDiv(planet) {
            $("<div></div>").appendTo("body").addClass('popup').css({
                left: window.mouseX,
                top: window.mouseY
            }).text("You are viewing planet " + planet.name + " which is " + planet.description)
        }

        function removePopUp() {
            $('.popup').remove();
        }

        function showNextPlanets() {
            console.log("Show next planets");
            $('.planetBase').remove();
            for(i = 0; i < 3; i++) {
                if (window.planet_index + i === window.planets.length) {
                    window.planet_index = 0;
                }
                var planet = window.planets[window.planet_index + i];
                var width = $(window).width();
                var height = $(window).height();
                var left = Math.floor(Math.random() * (width-100));
                var top = Math.floor(Math.random() * (height-100));
                $("<div></div>").appendTo("body").css({
                    left: left,
                    top: top
                }).addClass('planetBase').attr('id', planet.id).text(planetToString(planet)).css('background', planet.color);
                $('.planetBase').hover(function () {
                    $.ajax(
                        {
                            type: 'get',
                            url: '/planet_info',
                            data: { 'planetID': this.id },
                            success: function (planet) {
                                showInfoDiv(planet);
                            },
                            error: function (response) {
                                alert("error " + response);
                            }
                        }
                    );
                }, function() {
                    removePopUp();
                }).click(function() {
                    $.ajax(
                        {
                            type: 'post',
                            url: '/add_expedition',
                            data: { 'planetID': this.id },
                            success: function (response) {
                                alert('Added to Expeditions!');
                            },
                            error: function (response) {
                                alert("error " + response);
                            }
                        }
                    );
                });
            }
            window.planet_index += 3;
        }

        $(document).mousemove(function(event) {
            window.mouseX = event.pageX;
            window.mouseY = event.pageY;
        });

        $(document).ready(function() {
            $("button").click(function() {
                console.log("Button clicked");
                showNextPlanets();
            });

            $.get("/planets", function(responseJson) {
                window.planets = responseJson;
                showNextPlanets();
            });
        });
    </script>
</head>
<body>

<p class="lead">Where would you like to travel?
    <button class="btn btn-dark">Next Galaxy ==></button>
</p><br>

</body>
</html>
