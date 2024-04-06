/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlets;

import com.bean.order;
import com.doa.applicationDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ELCOT
 */
@WebServlet("/orderHistory")
public class orderHistory extends HttpServlet{
    
    private static final long serialVersionUID= 1L;
    
        @Override
         public void  doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
         
             System.out.println("inside the order history");
             //get the username from session
             String username= (String)req.getSession().getAttribute("username");
             
             
             //call dao and get order history
             applicationDao dao=new applicationDao();
             
             List<order> orders = dao.getOrder(username);
             
             
             
             //set order data in request
             req.setAttribute("order",orders);
             
             //forward to home jsp
             req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
         }
}
