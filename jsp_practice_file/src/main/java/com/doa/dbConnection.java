/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ELCOT
 */
public class dbConnection {
    
    public static Connection getConnectionDatabase(){
     
        Connection conn=null;
        //load the driver class
     
         try {
            // load the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");

            // get hold of the driver manager
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hplus","root","Keerthi1405@");    
            System.out.println("Database successfully opened.");
            
        }
         catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        } 
         catch (SQLException e) {
            System.out.println("Connection Failed, check output console");
            e.printStackTrace();
        }

        if (conn != null) {
            System.out.println("Connection made to DB!");
        }
        return conn;
    }
}
