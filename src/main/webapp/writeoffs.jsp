<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 19.04.16
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.sql.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Списания</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@ include file="top.jsp"%>
<article>
    <%
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/sklad", "root", "root");
    Statement stmt = conn.createStatement();
        /*видимо строка запроса неправильная*/
    ResultSet rs = stmt.executeQuery("select orders.name as 'Заказ', furniture.name as 'Фурнитура'," +
            "minus.amount as 'Кол-во', minus.date as 'Дата' from orders, furniture, minus WHERE minus.order_id=orders.id " +
            "and minus.furniture_id=furniture.id ORDER BY minus.date DESC;");
    %><table><%
    ResultSetMetaData resMetaData = rs.getMetaData();
    int nCols = resMetaData.getColumnCount();
%><tr><%
    for (int kCol = 1; kCol <= nCols; kCol++) {
        out.print("<td><b>" + resMetaData.getColumnName(kCol) + "</b></td>");
    }
%></tr><%
    while (rs.next()) {
%><tr><%
    for (int kCol = 1; kCol <= nCols; kCol++) {
        out.print("<td>" + rs.getString(kCol) + "</td>");
    }
%></tr><%
    }
%></table><%
    conn.close();
%>



    Мммм... а как мы сюда списания зафигачим? Прямо в БД лезть низя. В дбСервис наверное тоже
    в модель добавить список списаний? а надо оно? Сделать вьюшку списаний в БД может? Еще бы как-то на каждую
    фурнитуру или заказ сделать просмотр списка списаний. Или все это сделать на этой страничке с возможностью выбора
    критерия поиска?!?

</article>
</body>
</html>
