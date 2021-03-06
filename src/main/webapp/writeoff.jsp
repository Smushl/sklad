<%@ page import="model.FurnitureDataSet" %>
<%@ page import="model.Model" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 01.04.16
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Списание фурнитуры</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<%@ include file="top.jsp"%>
<article>
<form method="post" action="write-off" autocomplete="off" >
<table>
    <caption><h2>Списание фурнитуры</h2></caption>
    <tr>
        <th><b>Название</b></th>
        <th><b>На складе</b></th>
        <th><b>Кол-во</b></th>
    </tr>
<%
    @SuppressWarnings("unchecked")
    List<FurnitureDataSet> furniture = (ArrayList<FurnitureDataSet>) session.getAttribute("basket");
    for (FurnitureDataSet fds : furniture){ %>
    <tr>
        <td><%= fds.getName() %></td>
        <td><%= fds.getInventory() %></td>
        <td><input type = 'number' name ='<%= fds.getId() %>' min = '0'></td>
    </tr>
    <% } %>
</table>

    <select name="order" title="заказ" style="width: 70%;">
        <% Model model = (Model) application.getAttribute("model");
            List <Order> orders = model.getOrders();
            for (Order order : orders){ %>
            <option value='<%= order.getId() %>'><%= order.getName() %></option>
            <% } %>
    </select>
    <button class="btn" type="submit">Списать</button>
</form>

</article>
</body>
</html>
