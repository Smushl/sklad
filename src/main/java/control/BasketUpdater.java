package control;

import model.FurnitureDataSet;
import model.Model;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by roman on 31.03.16.
 */
@WebServlet(name = "BasketUpdater", urlPatterns = "/basket")
public class BasketUpdater extends HttpServlet {
    final static Logger logger = Logger.getLogger(BasketUpdater.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<FurnitureDataSet> basket = (ArrayList<FurnitureDataSet>) session.getAttribute("basket");
        if (basket == null){
            basket = new ArrayList<>();
        }

        Enumeration<String> parameterNames = req.getParameterNames();
        Model model = (Model) getServletContext().getAttribute("model");
        while (parameterNames.hasMoreElements()){
            FurnitureDataSet fds = model.getFurnitureById(Integer.parseInt(parameterNames.nextElement()));
            if (!basket.contains(fds))
                basket.add(fds);
        }
        session.setAttribute("basket", basket);
        req.getRequestDispatcher("furniture.jsp").forward(req, resp);

    }
}
