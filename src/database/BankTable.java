package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BankTable {
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
            System.out.println("Creating bank table");
            statement = connection.createStatement();

            String sql = "CREATE TABLE BANK " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
            System.out.println("Bank Table created successfully");
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
        System.out.println("Created bank table");
    }

    public static void addBank(String name, Integer id) {
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

            String sql = "INSERT INTO Bank " +
                    "VALUES (" + id + ", '" + name + "')";
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
