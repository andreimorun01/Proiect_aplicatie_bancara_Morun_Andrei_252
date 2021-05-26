package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoryTable {
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
            System.out.println("Creating history table");
            statement = connection.createStatement();

            String sql = "CREATE TABLE HISTORY " +
                    " (timestamp BIGINT not NULL, " +
                    " action VARCHAR(255), " +
                    " thread VARCHAR(255), " +
                    " PRIMARY KEY ( timestamp ))";
            statement.executeUpdate(sql);
            System.out.println("History Table created successfully");
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
        System.out.println("Created history table");
    }

    public static void addHistoryElement(String action, String timestamp, String thread) {
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

            String sql = "INSERT INTO History " +
                    "VALUES (" + timestamp + ", '" +
                    action + "', '" + thread + "')";
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
        System.out.println("Added bank to the table");
    }
}
