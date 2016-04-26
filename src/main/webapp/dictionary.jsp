<%@page import="java.sql.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>MyDictionary</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<%@ include file="top.jsp"%>
    <article>
        <%
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/various_things", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from dictionary ORDER BY english;");
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
    </article>
</body>
</html>