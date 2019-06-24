package webapp;

import model.Authenticator;
import model.ExpeditionController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author joj on 6/24/2019
 **/
@WebServlet(name = "add_expedition")
public class add_expedition extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("username");
        int astronautID = Authenticator.getAstronautID(username);
        int planetID = Integer.parseInt(request.getParameter("planetID"));

        System.out.println(astronautID + " " + planetID);
        ExpeditionController expeditionController = new ExpeditionController();
        expeditionController.createExpedition(astronautID, planetID);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
