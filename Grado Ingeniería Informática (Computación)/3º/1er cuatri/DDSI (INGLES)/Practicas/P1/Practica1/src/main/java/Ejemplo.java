/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Cordon
 */

//STEP 1. Import required packages

import java.sql.*;

public class Ejemplo {
// JDBC driver name and database URL

    static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:oracle:thin:@172.17.20.75:1521:rabida";

    //  Database credentials
    static String USER = "DDSI_002";
    static String PASS = "DDSI_002";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {

            System.out.println("starting");
            // STEP 2. Register the JDBC driver
            Class.forName("oracle.jdbc.OracleDriver");
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT name, address, phone FROM students";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");

                //Display values
                System.out.print("Name: " + name);
                System.out.print(", Address: " + address);
                System.out.print(", Phone: " + phone);
                System.out.println("-");
            }

            stmt.close(); //Close the statement (El indio del video dice que hay que hacerlo)
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }
    }
}