<%--
  Created by IntelliJ IDEA.
  User: joj
  Date: 6/24/2019
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Error page</title>
</head>
<body>

<div class="content" style="width: 80%; margin: 20px auto">
    <p class="lead">
        Unfortunately an error occurred :( <br><br>
        ${errorMessage}
    </p><br>
</div>

</body>
</html>
