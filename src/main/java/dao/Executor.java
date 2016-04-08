package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by roman on 23.03.16.
 */
public class Executor {
    public static int execUpdate(Connection con, String update) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute(update);
        int updated = statement.getUpdateCount();
        statement.close();
        return updated;
    }
// Действительно ли тут нужен static? У Чибрикова в примере по-другому как-то сделано

    public static <T> T execQuery(Connection con, String query, ResultHandler<T> handler) throws SQLException{
        //тут любой шаг может вызвать SQLException, что с этим делать?
        Statement statement = con.createStatement();
        statement.execute(query);
        ResultSet rs = statement.getResultSet();
        T value = handler.handle(rs);
        rs.close();
        statement.close();
        return value;
    }
}
