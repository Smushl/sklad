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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by roman on 06.04.16.
 */

@WebServlet(name = "WriteOff", urlPatterns = "/write-off")
public class WriteOff extends HttpServlet {
    final static Logger logger = Logger.getLogger(WriteOff.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = (Model) getServletContext().getAttribute("model");
/*
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String param = parameterNames.nextElement();
            logger.info(param + ": " + request.getParameter(param));
        }
*/
        int orderId = Integer.parseInt(request.getParameter("order"));

        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<FurnitureDataSet> furniture = (ArrayList<FurnitureDataSet>) session.getAttribute("basket");

        for (FurnitureDataSet furnitureItem : furniture){
            int amount = Integer.valueOf(request.getParameter(String.valueOf(furnitureItem.getId())));
            int i = model.writeOff(furnitureItem, amount, orderId);
            if (i == 1)
                logger.info("Списание успешно: \"" + furnitureItem.getName() + "\" - " + amount + "шт списано успешно" );
            else
                logger.error("Ошибка списания: " + furnitureItem.getName());
        }
        session.removeAttribute("basket"); //в случае успеха очищаем корзинку

        RequestDispatcher view = request.getRequestDispatcher("writeoffs.jsp");
        view.forward(request, response);

        //сделать проще, взять из сессии список фурнитуры, по id получить кол-во в списание
        //записать в Мару?
        //списывать по одному или пачкой? А пачкой как? попробуем по одному
        //вызвать model.writeOff
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
