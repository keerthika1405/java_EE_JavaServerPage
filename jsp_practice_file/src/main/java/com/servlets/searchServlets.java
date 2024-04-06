/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servlets;

import com.bean.product;
import com.doa.applicationDao;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ELCOT
 */
@WebServlet("/search")
public class searchServlets extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
           try {
               String searchString= req.getParameter("search");
                     
               //searchString is basically procure from the  text  box of the search that user enter//session
               req.getSession().setAttribute("search",searchString );
                     
              //get the connection from the servletContext of listener
//              Connection conn= (Connection)getServletContext().getAttribute("dbConnect");//listeners

               //call Dao layer to get all product for search criteria
               applicationDao dao=new applicationDao();
               List<product> productsearchedList = dao.searchProduct(searchString);//fpr listener add (searchString,conn)
               
               //write the product data back to the client browser
//               String page= getHTMLString( req.getServletContext().getRealPath("/html/search.html"), productsearchedList);    
//               resp.getWriter().write(page);

//instead, write for jsp
                req.setAttribute("product",productsearchedList);
                req.getRequestDispatcher("/html/searchResults.jsp").forward(req, resp);

           } 
           catch (SQLException ex) {
               Logger.getLogger(searchServlets.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
    public String getHTMLString(String filePath,List<product> prod) throws FileNotFoundException, IOException{
        
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
    
    page = MessageFormat.format(page, 
            prod.get(0).getProductImagePath(),
            prod.get(1).getProductImagePath(),
            prod.get(2).getProductImagePath(),
            prod.get(0).getProductName(),
            prod.get(1).getProductName(),
            prod.get(2).getProductName(),0  );
    
    return page;
    }
}
