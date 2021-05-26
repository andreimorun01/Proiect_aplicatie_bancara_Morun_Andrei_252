package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LoanTable {
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

            String sql = "CREATE TABLE LOANS " +
                    "(id INTEGER not NULL, " +
                    " value VARCHAR(255), " +
                    " currency VARCHAR(255), " +
                    " duration VARCHAR(255), " +
                    " rate VARCHAR(255), " +
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

    public static void addLoan(String value, String currency, String duration, String rate, Integer id) {
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

            String sql = "INSERT INTO Loans " +
                    "VALUES (" + id + ", '" + value + "', '" +
                    currency + "', '" + duration + "', '" +
                    rate + "')";
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
        System.out.println("Added loan to the table");
    }
}
