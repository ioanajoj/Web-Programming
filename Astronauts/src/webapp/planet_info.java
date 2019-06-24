package webapp;

import com.google.gson.Gson;
import domain.Planet;
import model.PlanetController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author joj on 6/24/2019
 **/
@WebServlet(name = "planet_info")
public class planet_info extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long planetID = Long.parseLong(request.getParameter("planetID"));

        PlanetController planetController = new PlanetController();
        List<Planet> planets = planetController.getPlanets();
        Planet planet = planets
                .stream()
                .filter(p -> p.getId().equals(planetID))
                .findFirst().get();
        String json = new Gson().toJson(planet);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
