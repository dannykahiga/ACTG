package Database_Controller;

import javax.swing.*;
import java.sql.*;

public class Database_Handler {

    public static Database_Handler database_handler = null;
    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static Statement statement = null;

    public Database_Handler() {
        Create_Connection();
    }

    public static Database_Handler getInstance() {
        if (database_handler == null) {
            database_handler = new Database_Handler();
        }
        return database_handler;
    }

    void Create_Connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://127.0.01:3306/actgdb", "root", "root");
            System.out.println("Database Connection Successful");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Exception at execQuery:dataHandler" + e.getLocalizedMessage());
            return null;
        } finally {

        }
        return resultSet;
    }

    public boolean executeAction(String Query) {
        try {
            statement = connection.createStatement();
            statement.execute(Query);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage(), "Error Occurred", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + e.getLocalizedMessage());
            return false;
        } finally {

        }
    }
}

