package control;

import model.FurnitureDataSet;
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
 * Created by Некрасов on 27.04.2016.
 */
@WebServlet(name = "FurnitureAlone", urlPatterns = "/furniture_alone")
public class FurnitureAlone extends HttpServlet {
    final static Logger logger = Logger.getLogger(FurnitureAlone.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = (Model) getServletContext().getAttribute("model");
        int id = Integer.parseInt(request.getParameter("f"));
        FurnitureDataSet fds = model.getFurnitureById(id);
        logger.info("Furniture: " + fds.getName());
        request.setAttribute("fds", fds);
        RequestDispatcher view = request.getRequestDispatcher("/furniture_alone.jsp");
        view.forward(request, response);
    }
}
