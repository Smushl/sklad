<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="model.FurnitureDataSet" %>
<%@ page import="model.FurnitureGroupDataSet" %>
<%@ page import="model.Model" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <!--META http-equiv="Content-Type" content="text/html; charset=UTF-8"/-->
    <title>Furniture.JSP</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@ include file="top.html"%> <%--шапка--%>

<%int group = (request.getParameter("group") == null) ? 0 : Integer.parseInt(request.getParameter("group"));%>
<%String name_part = (request.getParameter("name_part") == null) ? "" : (String)request.getParameter("name_part");%>

<aside>
    <%
    Model model = (Model) application.getAttribute("model");
    ArrayList<FurnitureGroupDataSet> furnitureGroupDataSets = model.getFurnitureGroups();

    for (FurnitureGroupDataSet furnitureGroupDataSet : furnitureGroupDataSets){
        out.print("<a href='furniture.jsp?group=" + furnitureGroupDataSet.getId() + "'>");
        if (furnitureGroupDataSet.getId() == group)
            out.print("<b>" + furnitureGroupDataSet.getName() + "</b>");
        else
            out.print(furnitureGroupDataSet.getName());
        out.print("</a><br>\n");
    }
    %>
    <a href='furniture.jsp'>Вся фурнитура</a><br><br>
    <!--div style="text-align: center;"-->
        <form method="get" action="furniture.jsp" autocomplete="off" >
            <input type="text" name="name_part" placeholder="Введите часть названия фурнитуры" required style="width: 150px;">
            <input type='submit' value='Найти!'>
        </form>
    <!--/div-->
</aside>
<div class = 'basket'>
    <b>Корзина</b><br>
        <%
            HttpSession session1 = request.getSession();
            List<FurnitureDataSet> furniture = (ArrayList<FurnitureDataSet>) session1.getAttribute("basket");
            if (furniture != null)
                for (FurnitureDataSet fds : furniture){
                    out.print(fds.getName() + "<br>");
                }

        %>
    <form method="post" action="receipt.jsp">
        <button class="btn" type="submit">Списание</button>
    </form>
    <form method="post" action="receipt.jsp">
        <button class="btn" type="submit">Приход</button>
    </form>
</div>
<article>
<form method="post" action="basket" autocomplete="off" >
<table border=0>
    <caption><h2>Список фурнитуры</h2></caption>
    <tr>
    <th><b>Название</b></th>
    <th><b>Кол-во на складе</b></th>
    <th></th>
    </tr>
    <%--<tbody>--%>

    <%
        ArrayList<FurnitureDataSet> furnitureList = model.getFurnitureList();

        if (name_part.isEmpty()) {
            if (!(furnitureList.isEmpty() || furnitureList == null)){
                for (FurnitureDataSet fds : furnitureList){
                    if ((group == 0) || (fds.getGroup() == group)) {
                        out.print("<tr><td><a href = 'furniture_alone.jsp?f="+ fds.getId() + "'>" + fds.getName() + "</a></td> <td>"
                                + fds.getInventory() + "</td><td><input type='checkbox' name = " + fds.getId() + "></td></tr>\n");
                    }
                }
            }
        }else {
            if (!(furnitureList.isEmpty() || furnitureList == null)){
                for (FurnitureDataSet fds : furnitureList){
                    if (fds.getName().toLowerCase().contains(name_part.toLowerCase())){
                        out.print("<tr><td><a href = 'furniture_alone.jsp?f="+ fds.getId() + "'>" + fds.getName() + "</a></td> <td>"
                                + fds.getInventory() + "</td><td><input type='checkbox' name = " + fds.getId() + "></td></tr>\n");
                    }
                }
            }
        }
    %>


    <%--</tbody>--%>
</table>
<%--<input type="hidden" name="group" value="<%=group%>">--%>
<%--<input type="submit">--%>
<button class="btn" type="submit">Добавить</button>
</form>
<br>
<br>
</article>
</body>
</html>