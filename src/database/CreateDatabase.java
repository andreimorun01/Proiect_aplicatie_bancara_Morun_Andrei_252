package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
    /**
     * Creates the database for the application from the mysql server.
     * @param args not used
     */
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try{
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Opening connection
            System.out.println("Connecting to database");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "Andrew9901!@");

            // Executing query
            System.out.println("Creating database");
            statement = connection.createStatement();

            String sql = "CREATE DATABASE PROJECT";
            statement.executeUpdate(sql);
            System.out.println("Database created successfully");
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
        System.out.println("Created database");
    }
}