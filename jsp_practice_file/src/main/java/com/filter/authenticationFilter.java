/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ELCOT
 */
public class authenticationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      
        HttpServletRequest request=(HttpServletRequest)req;
        if(request.getRequestURI().startsWith("/javaserverPage/orderHistory")||
           request.getRequestURI().startsWith("/javaserverPage/getProfileDetails")){
            
        HttpSession session=request.getSession();
        if(session.getAttribute("username")==null){ 
        request.getRequestDispatcher("/html/login.jsp").forward(request, resp);
        }
        }
        //we have to call the servlet by filterchain
        //very important and critical statement that you need to write in the code, 
        //this line now going to take control to the next servlet
        //the next servlet is which ever the servlet the user tried to access from the browser
        chain.doFilter(req, resp);//must

    }

    @Override
    public void destroy() {
        Filter.super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
    
}
