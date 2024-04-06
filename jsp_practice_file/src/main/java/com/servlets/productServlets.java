/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlets;

import com.bean.product;
import com.doa.applicationDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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


@WebServlet("/addProduct")
public class productServlets extends HttpServlet {
    
      @Override
      public void  doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
              ServletException{
          
          //get the httpsession object
          HttpSession session = req.getSession(); 
          //create a cart as arraylist for the user(tracking)
          
          //here i not going to create a new arraylist for the user from the session becz , for user not to lost their old cart list 
          //List<String> cart= new ArrayList<>();
          //so we try to get it from the session like below
          List<String> cart = (ArrayList<String>) session.getAttribute("cart"); //this is for all the subsequesnt request
                             
          //safe check, if you do not get the cart at all then creating a new arraylist
          if(cart==null){    
          cart = new ArrayList<String>();
          }
          
          //add the selected product to the cart
          if(req.getParameter("product")!= null){
          cart.add(req.getParameter("product"));
          }
          //set the list to the session object as an attributes
          session.setAttribute("cart", cart);
          
          
          System.out.println(cart);
          
           try {
               
           //get the search critera from the searchServlets 
           String search =(String)session.getAttribute("search");
           
          
//            //get the connection from the servletContext of listener
//              Connection conn= (Connection)getServletContext().getAttribute("dbConnect");
           
           //get the searchResult from the dao
           applicationDao dao=new applicationDao();
           //caching API is better  to maintain searchResults , caching is going to hold a lot of data beczif you trying to order
           //the  database  for every single call then, it is going to be a very tedious process and it'll will pull down 
           //the performance--like a project in a industry
                   
           
           //listener reflect
            List<product> products = dao.searchProduct(search);
            
//add a conection in later point
              
            //set the search resultin request scope or attributes
            req.setAttribute("product", products);
         
                   
            //forward to the searchResult.jsp
            req.getRequestDispatcher("/html/searchResults.jsp").forward(req, resp);
            } 
           catch (SQLException ex) {
               
               Logger.getLogger(searchServlets.class.getName()).log(Level.SEVERE, null, ex);
           }
      }
      
      
      
      
//      
//    @Override
//protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    
//}
}
//    HttpSession session = req.getSession();
//    String username = (String) session.getAttribute("username");
//    List<String> cart = (ArrayList<String>) session.getAttribute("cart");
//    
//    try {
//        applicationDao dao = new applicationDao();
//        for (String product : cart) {
//            // Add each product from the cart to the order table
//            dao.addProductToOrder(username, product);
//        }
//        
//        // Clear the cart after adding products to the order
//        session.removeAttribute("cart");
//        
//        // Retrieve the updated order history
//        List<order> orders = dao.getOrder(username);
//        
//        // Set the updated order data in the request attribute
//        req.setAttribute("order", orders);
//        
//        // Forward to the order history JSP to display the updated order history
//        req.getRequestDispatcher("/html/orderHistory.jsp").forward(req, resp);
//    } 
//    catch (SQLException ex) {
//        Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
//        // Handle the error appropriately, e.g., display an error message to the user
//        req.setAttribute("error", "Failed to add products to the order. Please try again.");
//        req.getRequestDispatcher("/html/error.jsp").forward(req, resp);
//    }
//}

        
//}
