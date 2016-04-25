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
 *
 * Created by roman on 25.03.16.
 */

@WebServlet(name = "ManagerAdd", urlPatterns = "/manager_add")
public class ManagerAdd extends HttpServlet {
    final static Logger logger = Logger.getLogger(ManagerAdd.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");

        logger.info("POST request received from " + req.getRemoteAddr() + " name = " + name);

        Model model = (Model) getServletContext().getAttribute("model");
        if (model.addNewManager(name) > 0){
            RequestDispatcher view = req.getRequestDispatcher("managers.jsp");
            view.forward(req, resp);
        }
        else{
            req.setAttribute("errorMsg", "Ошибка добавления нового мэнеджера");
            RequestDispatcher view = req.getRequestDispatcher("error404.jsp");
            view.forward(req, resp);
        }


    }
}
