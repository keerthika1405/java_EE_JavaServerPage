/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlets;

import com.bean.user;
import com.doa.applicationDao;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ELCOT
 */
@WebServlet("/getProfileDetails")
public class viewProfileServlets extends  HttpServlet{
    
     public void  doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
     
         //get the username from  the session
         System.out.println("user name in profile Servlets "+req.getSession().getAttribute("username"));
         
         //jsp standard action 
         //call the dao to get profile detail
         String username=(String)req.getSession().getAttribute("username");
         applicationDao dao=new applicationDao();
         user user=dao.getprofileDetail(username);
         
         //for Expression language to create weight summary
         Map<String , Double> weightSummary =new HashMap<>();
         weightSummary.put("january", 98.2);
         weightSummary.put("february", 99.2); 
         weightSummary.put("march", 68.2);
          
         //store all informationin request object
         req.setAttribute("user", user);
         req.setAttribute("weightSummary", weightSummary);
         
         //forward the control to the profile.jsp
         req.getRequestDispatcher("/html/profile.jsp").forward(req, resp);
     }
     
}
