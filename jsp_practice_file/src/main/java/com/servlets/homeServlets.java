/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlets;

import com.doa.dbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ELCOT
 */

public class homeServlets extends HttpServlet{
    //life cycle of servlets-->init ,service, destroy
    
    public Connection conn=null;
    
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        System.out.println("in service(doGet) methods");
        //forward the control to the index.html
        req.getRequestDispatcher("index.html").forward(req, resp);
    }
    
    @Override
    public void init() throws ServletException{
    
        System.out.println("in init methods");
        //initialization activity--set up db connection object
       conn = dbConnection.getConnectionDatabase();
    }
     @Override
     public void destroy() {
    
        System.out.println("in destroy methods");
        try {
            //clean up activity-- close the db connection object
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(homeServlets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
