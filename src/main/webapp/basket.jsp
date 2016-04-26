<%@ page import="model.FurnitureDataSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 06.04.16
  Time: 0:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
<div class = 'basket'>
    <table>
        <caption><h2>Списание фурнитуры</h2></caption>
        <tr>
            <th><b>Название</b></th>
        </tr>
        <%
            HttpSession session1 = request.getSession();
            @SuppressWarnings("unchecked")
            List<FurnitureDataSet> furniture = (ArrayList<FurnitureDataSet>) session1.getAttribute("basket");
            if (furniture != null)
                for (FurnitureDataSet fds : furniture){
                    out.print("<tr><td>" + fds.getName() + "</td></tr>");
                }

        %>
    </table>
</div>

</body>
</html>
