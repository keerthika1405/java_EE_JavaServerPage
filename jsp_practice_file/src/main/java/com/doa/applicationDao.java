/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.doa;

import com.bean.order;
import com.bean.product;
import com.bean.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ELCOT
 */
//doa layer-->conversing with the database-->talk and connect with the database
public class applicationDao {
    
    public List<product> searchProduct(String searchString) //listeners add(string search,Connection conn)
            throws SQLException{
        
        product prod=null;
        List<product> prods=new ArrayList<>();
        
        try{
        Connection conn = dbConnection.getConnectionDatabase();
        
        String sql=" select * from product where productName like  '%"+searchString+"%'";
        
        Statement stat= conn.createStatement();
        
        ResultSet result= stat.executeQuery(sql);
        
        while (result.next()){
        prod=new product();
        prod.setProductId(result.getInt("productId"));
        prod.setProductName(result.getString("productName"));
        prod.setProductImagePath(result.getString("productImagePath"));
        
        prods.add(prod);
        }
      }
      catch(SQLException ex){
          ex.printStackTrace();
        }
        
        return prods;
    }
    
    
    public int registerUser(user user){
        int affectedRow=0;
      try{
        Connection conn = dbConnection.getConnectionDatabase();
        
        String sql="insert into user values(?,?,?,?,?,?)";
        
        //preparedStatement demands as pre-compile query
        
        PreparedStatement prestat= conn.prepareStatement(sql);
        prestat.setString(1,user.getUsername());
        prestat.setString(2,user.getPassword());
        prestat.setString(3,user.getFirstName());
        prestat.setString(4,user.getLastName());
        prestat.setInt(5,user.getAge());
        prestat.setString(6,user.getActivity());

        affectedRow =prestat.executeUpdate();

                
        
        
      }
      catch(SQLException ex){
         ex.printStackTrace();
      }
      return affectedRow;
      
    }
    
    
    
    public boolean validateUser(String username , String password){
    boolean isvalidateUser=false;
    try{
        //get the connection for the database
        Connection conn=dbConnection.getConnectionDatabase();
        
        //write the select query
        String sql="select * from user where username=? and password=?";
        //set parameter with preparedStatement
        
           PreparedStatement prestat= conn.prepareStatement(sql);
             prestat.setString(1,username);
             prestat.setString(2,password);
        //execute the statement check where the user exist
        
        ResultSet result=prestat.executeQuery();
        
        while(result.next()){
        isvalidateUser=true;
        }
    }
    catch(SQLException ex){
        ex.printStackTrace();
    }
    return isvalidateUser;
    }
    
   public user getprofileDetail(String username){
   
       user user=null;
       try{
           //get connection to database
           Connection conn=dbConnection.getConnectionDatabase();
           
           //write a select querry to get profile details
           String sql= "select * from  user where username=?";
           PreparedStatement prestat=conn.prepareStatement(sql);
           prestat.setString(1, username);
           
           //execute query, get resultSet and  return user info
           ResultSet result=prestat.executeQuery();
       
           while(result.next()){
           user =new user();
           user.setUsername(result.getString("username"));
           user.setFirstName(result.getString("firstName"));
           user.setLastName(result.getString("lastName"));
           user.setActivity(result.getString("activity"));
           user.setAge(result.getInt("age"));
           }
           
       }
       catch(SQLException ex){
       ex.printStackTrace();
       }
       return user;
   } 
   
   public List<order> getOrder(String username){
   
       System.out.println("username from order"+username);
       
       order order = null;
       List<order> orders =new ArrayList<>();
       try{
        //get connection to database
        Connection conn=dbConnection.getConnectionDatabase();
        
        //write select query to get  order details
        String sql= "select * from orders where username=?";
        
        PreparedStatement prestat=conn.prepareStatement(sql);
        prestat.setString(1, username);
        
        //execute query, get resultSet and return user info
        ResultSet result=prestat.executeQuery();
        while(result.next())
        {
            order=new order();
            order.setOrderId(result.getInt("orderId"));
            order.setProductName(result.getString("productName"));
            order.setProductImagePath(result.getString("productImagePath"));
            order.setOrderDate(new Date(result.getDate("orderDate").getTime()));
            order.setUsername(result.getString("username"));      
            orders.add(order);
        }   
        System.out.println(" getting the order"+orders);
       }
       catch(SQLException ex){
       ex.printStackTrace();
       }
       return orders;
   }
   
   
//   
//   
//    // Method to add a product to the order table for the given username
//    public void addProductToOrder(String username, String productName, String imagePath) throws SQLException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        
//        try {
//            conn = dbConnection.getConnectionDatabase(); // Obtain connection
//            
//            // SQL statement to insert a product into the order table
//            String sql = "INSERT INTO orders (product_name, image_path, order_date, user_name) VALUES (?, ?, CURDATE(), ?)";
//            
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, productName); // Set product name
//            stmt.setString(2, imagePath); // Set image path
//            stmt.setString(3, username); // Set username
//            
//            // Execute the insert statement
//            stmt.executeUpdate();
//        } finally {
//            // Close resources in a finally block to ensure they're always closed
//            if (stmt != null) {
//                stmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
}
