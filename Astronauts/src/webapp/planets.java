package webapp;

import com.google.gson.Gson;
import domain.Planet;
import model.PlanetController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * @author joj on 6/24/2019
 **/
@WebServlet(name = "planets")
public class planets extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanetController planetController = new PlanetController();
        List<Planet> planetList = planetController.getPlanets();

        String json = new Gson().toJson(planetList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
