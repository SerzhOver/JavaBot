package DataBase;

import java.sql.*;

public class ConnectionDB {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "762541234";
    private static final String URL = "jdbc:mysql://localhost:3306/student";
    private static String NameStation = "SELECT nameStation FROM station";
    private static String ID = "SELECT idstation FROM station WHERE nameStation = ?";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;


    public static Boolean getNameStation(String msg) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(NameStation);

            while (resultSet.next()) {
                if (resultSet.getString("nameStation").equals(msg)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static String getID(String msg) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            preparedStatement = connection.prepareStatement(ID);
            preparedStatement.setString(1, msg);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getString("idstation");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
