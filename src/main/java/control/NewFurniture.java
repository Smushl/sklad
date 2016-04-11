package control;

import model.Model;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TomcatServ
 * Created by roman on 18.03.16.
 */
@WebServlet(name = "NewFurniture", urlPatterns = "/furniture")
public class NewFurniture extends HttpServlet {
    final static Logger logger = Logger.getLogger(NewFurniture.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String group_id = req.getParameter("group");
        logger.info("POST request received from " + req.getRemoteAddr() + " name = " + name + " group_id = " + group_id);
        Model model = (Model) getServletContext().getAttribute("model");
        model.addNewFurniture(name, group_id);
        RequestDispatcher view = req.getRequestDispatcher("furniture.jsp");
        view.forward(req, resp);
    }
}
