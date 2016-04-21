<%@ page import="model.FurnitureGroupDataSet" %>
<%@ page import="model.Model" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 27.03.16
  Time: 23:26
  To change this template use File | Settings | File Templates.
  Страничка для отображения результата дейсвий по внесению изменений в фурнитуру
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Результат</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@ include file="top.html"%>
<aside>
    <%
        Model model = (Model) application.getAttribute("model");
        ArrayList<FurnitureGroupDataSet> furnitureGroupDataSets = model.getFurnitureGroups();

        for (FurnitureGroupDataSet furnitureGroupDataSet : furnitureGroupDataSets){
            out.print("<a href='furniture.jsp?group=" + furnitureGroupDataSet.getId() + "'>");
            out.print(furnitureGroupDataSet.getName());
            out.print("</a><br>\n");
        }
    %>
    <a href='furniture.jsp'>Вся фурнитура</a><br><br>
    <form method="get" action="furniture.jsp" autocomplete="off" >
        <input type="text" name="name_part" placeholder="Введите часть названия фурнитуры"
               required style="width: 150px;">
        <input type='submit' value='Найти!'>
    </form>
</aside>

<div style="text-align: center; padding-top: 50px; font-size: 18px;">
<p><%=(String)request.getAttribute("result_msg") %></p>
</div>
</body>
</html>
