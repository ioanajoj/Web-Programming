package model;

import domain.Planet;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author joj on 6/24/2019
 **/
public class PlanetController {

    private static final String URL = "jdbc:postgresql://localhost:5432/Astronauts";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "unicorn";

    public List<Planet> getPlanets() {
        List<Planet> planets = new ArrayList<>();
        String sql = "SELECT P.ID, P.Name, P.Color, P.Description FROM Planets P";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            planets = this.resultSetParser(resultSet);
            Collections.shuffle(planets);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planets;
    }

    private List<Planet> resultSetParser(ResultSet resultSet) throws SQLException {
        List<Planet> planets = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("ID");
            String name = resultSet.getString("name");
            String color = resultSet.getString("color");
            String description = resultSet.getString("description");
            Planet planet = new Planet(id, name, color, description);
            planets.add(planet);
        }
        return planets;
    }
}
