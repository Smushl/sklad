<%@ page import="model.Manager" %>
<%@ page import="model.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Менеджеры</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@ include file="top.jsp"%>
<article>
    <table>
        <caption><h3>Менеджеры</h3></caption>
        <tr>
            <td><b>Id</b></td>
            <td><b>ФИО</b></td>
            <td><b>Активность</b></td>
        </tr>
        <%
            Model model = (Model) application.getAttribute("model");
            for (Manager manager : model.getManagers()){
                out.print("<tr>" +
                        "<td>" + manager.getId() + "</td>" +
                        "<td>" + manager.getName() + "</td>" +
                        "<td>" + manager.isActive() + "</td>" +
                        "</tr>\n");
            }
        %>
    </table>
    <br>
    <form method='POST' action='manager_add' autocomplete='off'>
        <fieldset>
            <legend>Новый менеджер</legend>
            <input name='name' type='text' title="Имя" required>
            <input type='submit' value='Добавить'>
        </fieldset>
    </form>
</article>
</body>
</html>
