package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(urlPatterns="/getServlet",initParams= @WebInitParam(name="URL",value="http:/www.weatherservice.com"))

@WebServlet("/getServlet")
public class getServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        
        //ServletConfig
        ServletConfig config=getServletConfig();
        System.out.println(config.getInitParameter("ConfigURL"));
         
        //ServletContext
//        ServletContext context=getServletContext();
//        System.out.println(context.getInitParameter("dbURL"));
        
        
        String value= req.getParameter("name");
        String htmlResponse = "<html><h1>Testing servlets</h1></html>";
        PrintWriter writer = resp.getWriter();
        writer.write(htmlResponse+" "+value);

    }
}
