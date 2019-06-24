package model;

import java.sql.*;

/**
 * @author joj on 6/24/2019
 **/
public class Authenticator {

    private static final String URL = "jdbc:postgresql://localhost:5432/Astronauts";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "unicorn";

    public static Boolean isValidAstronaut(String username, String password) {
        String sql = "SELECT A.Name FROM Astronauts A WHERE A.Name = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getAstronautID(String username) {
        String sql = "SELECT A.ID FROM Astronauts A WHERE A.Name = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
