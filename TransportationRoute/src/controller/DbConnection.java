package controller;

import model.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author joj on 5/8/2019
 **/
public class DbConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/TransportationRoute";
//    private static final String USERNAME = System.getProperty("username");
//    private static final String PASSWORD = System.getProperty("password");
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "unicorn";

    public void createRoute() {
        String sql = "drop table if exists Route;" +
                     " create table Route (" +
                     " rid serial primary key," +
                     " stationID serial);";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Station> getRoute() {
        List<Station> stations = new ArrayList<>();
        String sql = "select R.stationID as sid, S.sName as sname " +
                     "from Route R inner join Station S on R.stationID = S.sid " +
                     "order by R.rid;";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            stations = this.resultSetParser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    public void addStationToRoute(Station station) {
        String sql = "insert into Route (stationID) values (" + station.getSid() + ")";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Station> getNextStations(Station currentStation) {
        List<Station> stations = new ArrayList<>();
        String sql = "select sid, sname " +
                     "from Station S inner join Path P on S.sid = P.sid2 " +
                     "where sid1 = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, currentStation.getSid());
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            stations = this.resultSetParser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    public Station getStation(int stationID) {
        String sql = "select * " +
                     "from Station " +
                     "where sid = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, stationID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String stationName = resultSet.getString("sname");
                return new Station(stationID, stationName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Station> getAllStations() {
        List<Station> stations = new ArrayList<>();
        String sql = "select sid, sname " +
                     "from Station ";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            stations = this.resultSetParser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }

    /**
     * Get the penultimate Station in the Route
     * The last one being the current one
     * @return Station
     */
    public Station getLastStation() {
        String sql = "select st.sid as sid, st.sname as sname " +
                     "from (select R.stationID as sid, S.sName as sname " +
                     "      from Route R inner join Station S on R.stationID = S.sid " +
                     "      order by rid desc " +
                     "      limit 2) st " +
                     "order by st.sid " +
                     "limit 1;";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int stationID = resultSet.getInt("sid");
                String stationName = resultSet.getString("sname");
                return new Station(stationID, stationName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeLastTwoStationsInPath() {
        String sql = "delete from Route " +
                     "where rid in (select rid " +
                     "              from Route " +
                     "              order by rid desc " +
                     "              limit 2);";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Station> resultSetParser(ResultSet resultSet) throws SQLException {
        List<Station> stations = new ArrayList<>();
        while (resultSet.next()) {
            int stationID = resultSet.getInt("sid");
            String stationName = resultSet.getString("sname");
            Station station = new Station(stationID, stationName);
            stations.add(station);
        }
        return stations;
    }
}
