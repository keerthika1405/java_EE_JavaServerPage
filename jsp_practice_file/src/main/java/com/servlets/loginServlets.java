/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlets;

import com.doa.applicationDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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

@WebServlet("/login")
public class loginServlets extends HttpServlet{
    
  public void  doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
      
      RequestDispatcher dispatch=req.getRequestDispatcher("/html/login.jsp");
      dispatch.forward(req, resp);
  
  }
  
   @Override
   protected void  doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
       //get the  username from the loginform
       String username=req.getParameter("username");
       String password=req.getParameter("password");
       
       //validation using filter
       //call dao for validate the logic
       applicationDao dao = new applicationDao();
       boolean isValideUser = dao.validateUser(username, password);
       //check if user is invalid and set up an error message
       
       if(isValideUser){
       //set up the HTTP session object
        HttpSession session=req.getSession(true);
 
       //set the username as an attriburte
         session.setAttribute("username", username);
         
       //forward to the home.jsp
        req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
       }
       else{
       String errorMessage="Invalide credentail please login again";
       req.setAttribute("error", errorMessage);
       req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
       }
       
       
//       //set up the HTTP session object
//        HttpSession session=req.getSession();
// 
//       //set the username as an attriburte
//         session.setAttribute("username", username);
//         
//       //forward to the home.jsp
//        req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
   }
}
