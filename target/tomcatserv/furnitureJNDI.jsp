<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="java.sql.*"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@ page import="javax.naming.NamingException" %>

<html>
<head>
    <!--META http-equiv="Content-Type" content="text/html; charset=UTF-8"/-->
    <title>FurnitureJNDI.JSP</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <article>
        <%
            Context ctx = null;
            DataSource ds = null;
            Connection con = null;
            Statement statement = null;
            try {
                ctx = new InitialContext();
                ds = (DataSource)ctx.lookup("java:comp/env/jdbc/sklad");
            } catch (NamingException e) {
                e.printStackTrace();
            }

            try {
                con = ds.getConnection();
                statement = con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet rs = null;
            try {
                rs = statement.executeQuery("select * from sklad.furniture_view;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %><table border= "0"><%
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
        con.close();
        %>
    </article>
</body>
</html>