<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 27.03.16
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<article>
<p><%=(String)request.getAttribute("errorMsg") %></p>
</article>
</body>
</html>
