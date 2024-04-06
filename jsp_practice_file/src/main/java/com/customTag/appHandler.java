/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customTag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author ELCOT
 */
public class appHandler extends SimpleTagSupport{
    
    
    //attributes to instances
    private String format;
    private Date date;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public void doTag() throws JspException ,IOException{
    //format the date to the formate specified and write back to the jsp--> write back to the jsp we need a context
    //we need a surrounding scope and the scope for jsp is JspContext and JspWriter object
    
    SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
    PageContext context = (PageContext)getJspContext();
    JspWriter writer = context.getOut();
    writer.println(dateFormatter.format(date));
    }
}
