package control;

import model.Model;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by roman on 06.04.16.
 */
@WebServlet(name = "WriteOff", urlPatterns = "/write-off")
public class WriteOff extends HttpServlet {
    final static Logger logger = Logger.getLogger(WriteOff.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
//        Model model = (Model) getServletContext().getAttribute("model");
        while (parameterNames.hasMoreElements()){
            String param = parameterNames.nextElement();
            logger.info(param + ": " + request.getParameter(param));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
