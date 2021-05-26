package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientTable {
    public void create() {
        Connection connection = null;
        Statement statement = null;
        try{
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Opening connection
            System.out.println("Connecting to database");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/PROJECT",
                    "root", "Andrew9901!@");

            // Executing query
            System.out.println("Creating client table");
            statement = connection.createStatement();

            String sql = "CREATE TABLE CLIENTS " +
                    "(id INTEGER not NULL, " +
                    " firstName VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " address VARCHAR(255), " +
                    " birthday VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
            System.out.println("Client Table created successfully");
        } catch(Exception se) {
            se.printStackTrace();
        }
        finally{
            try{
                if(statement != null) {
                    statement.close();
                }
            }catch(SQLException ignored){
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Created client table");
    }

    public static void addClient(String firstName, String lastName, String address, String birthday, Integer id) {
        Connection connection = null;
        Statement statement = null;
        try{
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Opening connection
            System.out.println("Connecting to database");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/PROJECT",
                    "root", "Andrew9901!@");

            // Executing query
            System.out.println("Adding record");
            statement = connection.createStatement();

            String sql = "INSERT INTO Clients " +
                    "VALUES (" + id + ", '" + firstName + "', '" +
                    lastName + "', '" + address + "', '" +
                    birthday + "')";
            statement.executeUpdate(sql);
            System.out.println("Added bank record successfully");
        } catch(Exception se) {
            se.printStackTrace();
        }
        finally{
            try{
                if(statement != null) {
                    statement.close();
                }
            }catch(SQLException ignored){
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Added client to the table");
    }
}
