package blog.common;

import java.sql.*;

public class JDBCConnector {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5431/technicalblog";
    private static final String USER = "username";
    private static final String PASSWOR = "password";
    private Connection connection = null;
    private Statement statement = null;

    private JDBCConnector() {
        try {
            Class.forName(DB_DRIVER);

            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static JDBCConnector jdbcConnector = new JDBCConnector();

    public static JDBCConnector getInstant() {
        return jdbcConnector;
    }

    public ResultSet executeQuery(final String query) {

        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void execute(final String query) {
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        JDBCConnector jdbcConnector = JDBCConnector.getInstant();
//        jdbcConnector.execute("CREATE DATABASE technicalblog");

//        jdbcConnector.execute("create table Posts (id SERIAL PRIMARY KEY, title varchar, body varchar, date timestamp)");
    }
}
