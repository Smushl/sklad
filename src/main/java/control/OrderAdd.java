package control;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Некрасов on 22.03.2016.
 */
public class OrderAdd extends HttpServlet {
    final static Logger logger = Logger.getLogger(OrderAdd.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String project_price = req.getParameter("order_price");
        String dateStart = req.getParameter("date_start");
        String dateEnd = req.getParameter("date_end");

        logger.info("POST request received from " + req.getRemoteAddr() + " name = " + name + " project_price = " + project_price + " dateStart = " + dateStart + "dateEnd = " + dateEnd);
//        Model model = (Model) getServletContext().getAttribute("model");

//        model.addNewFurniture(name, group_id);


    }
}
