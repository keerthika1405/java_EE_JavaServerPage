/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlets;

import com.bean.product;
import com.bean.user;
import com.doa.applicationDao;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
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

@WebServlet("/register")
public class registerServlets extends HttpServlet{
 
    @Override
   protected void  doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
   
       //collect all the data
        String username = req.getParameter("username");
        String password = req.getParameter( "password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));
        String activity = req.getParameter("activity");
        
        
       //fill it up in a user bean
       user user=new user(username,password,firstName,lastName,age,activity);
       
       //call dao layer and save the user object to database
       applicationDao dao=new applicationDao();
       int row = dao.registerUser(user);
       
       String infoMessage=null;
       //prepare a information message  for user about the  success and failure of the opertaion
       if(row==0){
       infoMessage="an error occured, try again";
       }
       else{
       infoMessage="you have successfully registered";
       }
       //write the message back to the client in the browser
       
        String page= getHTMLString( req.getServletContext().getRealPath("/html/register.html"), infoMessage);    
        resp.getWriter().write(page);
   }
   
   
   public String getHTMLString(String filePath,String message) throws FileNotFoundException, IOException{
        
     //1.read the html as a string   
    BufferedReader read=new BufferedReader(new FileReader(filePath));
    
    String line="";
    StringBuffer buff=new StringBuffer();
    
    while((line=read.readLine())!=null){
    buff.append(line);
    }
    read.close();
    
    String page=buff.toString();
    
    //2.replace product image and product name in placeholder by messageFormate API
    
    page = MessageFormat.format(page, message  );
    
    return page;
    }
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
         String page= getHTMLString( req.getServletContext().getRealPath("/html/register.html"), " ");    
         resp.getWriter().write(page);
    
    }    
}
