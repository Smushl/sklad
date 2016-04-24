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
 * Created by roman on 27.03.16.
 */
@WebServlet(name = "FurnitureChange", urlPatterns = "/fur_change")
public class FurnitureChange extends HttpServlet {
    final static Logger logger = Logger.getLogger(FurnitureChange.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Model model = (Model) getServletContext().getAttribute("model");

        String msg = "";
        //-----------------DELETE---------------
        if (req.getParameter("id_to_remove") != null){
            int id = Integer.parseInt(req.getParameter("id_to_remove"));
            String furnitureName = model.getFurnitureById(id).getName();
            int result = model.removeFurniture(id);
            switch (result){
                case -1: msg = "Что-то пошло не так. Обратитесь к программисту";
                    logger.info("Что-то пошло не так при попытке удалить элемент, id = " + id);
                    break;
                case 0:  msg = "Вы пытаетесь удалить несуществующий элемент";
                    logger.info("Попытка удалить несуществующий элемент, id = " + id);
                    break;
                case 1:  msg = "Успешно удалено!";
                    logger.info("Успешно удалено: " + furnitureName);
                    break;
            }
            RequestDispatcher view = req.getRequestDispatcher("WEB-INF/result.jsp");
            req.setAttribute("result_msg", msg);
            view.forward(req, resp);
        }
        //------------------RENAME-----------------
        else if (req.getParameter("id_to_rename") != null){
            int id = Integer.parseInt(req.getParameter("id_to_rename"));
            String oldName = model.getFurnitureById(id).getName();
            String newName = req.getParameter("new_name");
            int result = model.renameFurniture(id, newName);
            switch (result){
                case -1: msg = "Что-то пошло не так. Обратитесь к программисту";
                    logger.info("Что-то пошло не так при попытке переименовать элемент, id = " + id);
                    break;
                case 0:  msg = "Вы ввели то же самое название";
                    logger.info("Попытка переименовать элемент тем же названием, id = " + id + " Старое название '" + oldName + "' Новое" +
                            " название '" + newName + "'");
                    break;
                case 1:  msg = "Переименовано успешно!";
                    logger.info("'" + oldName + "' успешно переименовано в '" + newName +"'");
                    break;
            }
            RequestDispatcher view = req.getRequestDispatcher("furniture_alone.jsp?f=" + id);
//            req.setAttribute("result_msg", msg);
            view.forward(req, resp);
        }
        //-----------------MOVE----------------
        else if (req.getParameter("id_to_move") != null){
            int id = Integer.parseInt(req.getParameter("id_to_move"));
            int group_id = Integer.parseInt(req.getParameter("new_group"));
            int result = model.moveFurnitureToGroup(id, group_id);
            //Если указываешь ту же группу, запрос проходит успешно
            switch (result){
                case -1: msg = "Что-то пошло не так. Обратитесь к программисту"; break;
                case 0:  msg = "0 - хз, что это значит. Может фурнитура уже в этой группе?!"; break;
                case 1:  msg = "Перемещение успешно!"; break; //а лучше сделать так, чтоб переместилось в прежнюю группу
            }
//            RequestDispatcher view = req.getRequestDispatcher("result.jsp");
            RequestDispatcher view = req.getRequestDispatcher("furniture.jsp?group=" + group_id);
//            req.setAttribute("result_msg", msg);
            view.forward(req, resp);
        }

    }



}
