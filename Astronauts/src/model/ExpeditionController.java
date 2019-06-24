package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author joj on 6/25/2019
 **/
public class ExpeditionController {
    private static final String URL = "jdbc:postgresql://localhost:5432/Astronauts";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "unicorn";

    public void createExpedition(int astronautID, int planetID) {
        String sql = "insert into Expeditions (astronautid, planetsid, deathprobability) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, astronautID);
            statement.setInt(2, planetID);

            // Compute death probability !
            int deathProbability = 1;
            statement.setInt(3, deathProbability);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
