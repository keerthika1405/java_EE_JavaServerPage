/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.listener;


import com.doa.dbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author ELCOT
 */

//@WebListener
public class applicationListener implements ServletContextListener{
    
   @Override
   public void contextDestroyed(ServletContextEvent dest){
   
       try {
           System.out.println("in contextDestroyed methods");
           Connection conn= (Connection)dest.getServletContext().getAttribute("dbConnect");
           conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(applicationListener.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
   @Override
   public void contextInitialized(ServletContextEvent init){
   
       System.out.println("in contextInitialized methods");
       Connection conn = dbConnection.getConnectionDatabase();
       init.getServletContext().setAttribute("dbConnect",conn);
   }
    
}
