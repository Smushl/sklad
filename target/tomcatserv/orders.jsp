<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <!--META http-equiv="Content-Type" content="text/html; charset=UTF-8"/-->
    <title>Заказы</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@ include file="top.html"%>
    <article>
            <%
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/sklad", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sklad.orders;");
            %><table><%
            ResultSetMetaData resMetaData = rs.getMetaData();
            int nCols = resMetaData.getColumnCount();
            %><tr><%
            for (int kCol = 2; kCol < nCols; kCol++) {
            out.print("<td><b>" + resMetaData.getColumnName(kCol) + "</b></td>");
            }
            %></tr><%
            while (rs.next()) {
            %><tr><%
            for (int kCol = 2; kCol < nCols; kCol++) {
            out.print("<td>" + rs.getString(kCol) + "</td>");
            }
            %></tr><%
            }
            %></table><%
            conn.close();
            %>
            <br>
            <form method='POST' action='addorder' autocomplete='off'>
                <fieldset>
                        <legend>Добавить заказ</legend>
                        <input name='name' type='text' placeholder='Название' required>&nbsp
                        <input name='order_price' placeholder='Цена проектировки' type='number' min='0' required><br><br>
                        <input name='date_start' placeholder='Дата запуска заказа' type='date' required>&nbsp
                        <input name='date_end' placeholder='Дата окончания' type='date' required><br>
                </fieldset>
                <p><input type='submit' value='Отправить'></p>
            </form>


    </article>
</body>
</html>