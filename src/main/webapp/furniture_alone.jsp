<%@ page import="model.FurnitureGroupDataSet" %>
<%@ page import="model.Model" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 26.03.16
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Редактирование</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<jsp:useBean id="fds" class="model.FurnitureDataSet" scope="request"/>
<%@ include file="top.jsp"%>
<%int furId = (request.getParameter("f") == null) ? 0 : Integer.parseInt(request.getParameter("f"));%>

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
        <input type="text" name="name_part" placeholder="Введите часть названия фурнитуры" required style="width: 150px;">
        <input type='submit' value='Найти!'>
    </form>
</aside>

<article>
    <table border=0>
        <caption><h1>Список фурнитуры</h1></caption>
        <tr>
            <th><b>Название</b></th>
            <th><b>Кол-во на складе</b></th>
            <th></th>
        </tr>
        <tr>
            <td><jsp:getProperty name="fds" property="name"/></td>
            <td><jsp:getProperty name="fds" property="inventory"/></td>
        <%--<tbody>--%>
<%--        <%
            ArrayList<FurnitureDataSet> furnitureList = model.getFurnitureList();
            if (furId != 0){
                if (!(furnitureList.isEmpty() || furnitureList == null)) {
                    for (FurnitureDataSet fds : furnitureList) {
                        if (fds.getId() == furId){
                            out.print("<tr><td>" + fds.getName() + "</td> <td>" + fds.getInventory() + "</td>");
                        }
                    }
                }
            }
        %>--%>

        <td>
                <form method="post" action="fur_change" autocomplete="off" >
                    <input type="hidden" name="id_to_remove" value="<%=furId%>">
                    <button class="btn_del" type="submit">х</button>
                </form>
            </td>
        </tr>
    </table>

    <br>
    <form method="post" action="fur_change" autocomplete="off" >
            <input type="hidden" name="id_to_rename" value="<%=furId%>">
            <input type="text" name="new_name" placeholder="Новое название" required style="width: 70%;">
            <button class="btn" type="submit">Переименовать</button>
    </form>
    <br>
    <form method="post" action="fur_change" autocomplete="off" >
        <input type="hidden" name="id_to_move" value="<%=furId%>">
        <select name="new_group" style="width: 70%;">
            <%
                List<FurnitureGroupDataSet> fgds = model.getFurnitureGroups();
                for (FurnitureGroupDataSet furnitureGroup : fgds){
                    out.print("<option value='"+ furnitureGroup.getId() + "'>" + furnitureGroup.getName() + "</option>\n");
                }
            %>
        </select>
        <button class="btn" type="submit">Переместить</button>
    </form>
    <p>Тут еще будет список послединх приходов и списаний<br><br><br>

</article>
</body>
</html>
