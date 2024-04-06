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

/**
 *
 * @author ELCOT
 */

@WebServlet("/linkedin")
public class linkedinServlets extends HttpServlet{
   
      public void  doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
          
          resp.sendRedirect("http://www.linkedin.com");
      }
}
