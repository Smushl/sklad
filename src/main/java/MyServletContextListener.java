import dao.DbService;
import model.Model;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by roman on 18.03.16.
 */
public class MyServletContextListener implements ServletContextListener {
    final static Logger logger = Logger.getLogger(MyServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext sc = sce.getServletContext();
        String dbUrl = sc.getInitParameter("dburl");
        String dbLogin = sc.getInitParameter("dblogin");
        String dbPass = sc.getInitParameter("dbpass");

        DbService dbService;
        Connection connection;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbLogin, dbPass);
            logger.info("Connection established");
            dbService = new DbService(connection);

        } catch (ClassNotFoundException e) {
            logger.error("Where is your MySQL JDBC Driver?", e);
            return;
        } catch (SQLException e) {
            logger.error("Connection failed", e);
            return;
        }

        Model model = new Model(dbService);
        sc.setAttribute("dbService", dbService);
        sc.setAttribute("model", model);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection con = (Connection) sce.getServletContext().getAttribute("dbConnection");
        logger.info("contextDestroyed");
        sce.getServletContext().removeAttribute("model");
        logger.info("model Destroyed");
        sce.getServletContext().removeAttribute("dbService");
        logger.info("dbService Destroyed");

        try {
            logger.info("Connection closing");
            con.close();
            logger.info("Connection closed");  //почему-то не логгируется ни "Connection closed", ни "Connection close failed"
        } catch (SQLException e) {
            logger.error("Connection close failed", e);
        }
    }
}
