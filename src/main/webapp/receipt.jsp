<%@ page import="model.FurnitureDataSet" %>
<%@ page import="model.Model" %>
<%@ page import="model.Seller" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Некрасов
  Date: 22.04.2016
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Приход</title>
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
                <th><b>Кол-во</b></th>
                <th><b>Цена</b></th>
            </tr>
            <%
                HttpSession session1 = request.getSession();
                @SuppressWarnings("unchecked")
                List<FurnitureDataSet> furniture = (ArrayList<FurnitureDataSet>) session1.getAttribute("basket");
                for (FurnitureDataSet fds : furniture){
                    out.print("<tr><td>" + fds.getName() + "</td><td><input type = 'number' name ='" + fds.getId() + "'min = '0'></td><td><input type = 'text'></td></tr>");
                }
            %>
        </table>

        <select name="seller" style="width: 70%;">
            <% Model model = (Model) application.getAttribute("model");
                List <Seller> sellers = model.getSellers();
                for (Seller seller : sellers){
                    out.print("<option value='"+ seller.getId() + "'>" + seller.getName() + "</option>\n");
                }
            %>
        </select>
        <button class="btn" type="submit">Списать</button>
    </form>

</article>

</body>
</html>
