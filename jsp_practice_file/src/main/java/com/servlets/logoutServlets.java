/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ELCOT
 */
@WebServlet("/logout")
public class logoutServlets extends HttpServlet{
    
     public void  doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        
          //set up the HTTP session object
        HttpSession session=req.getSession();
        
        session.invalidate();
        req.getRequestDispatcher("index.html").forward(req, resp);
     }
}
