/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import static restaurant.DBConnection.conn;

/**
 *
 * @author chatchai
 */
public class SQLFilter extends SQLRestaurant {
    
    /**
     * Select all Restaurant name from specific cuisine
     * @param cuisine 
     * @return name of all restaurant in cuisine
     */
    public static String[] selectFilterCuisineName(String cuisine){
        String[] name = null; 
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select RestName from Restaurant "
                    + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                    + "INNER JOIN Cuisine_Types USING (Cuisine_ID)"
                    + "WHERE Cuisine = \"" + cuisine 
                    + "\"");
            
            int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            name = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
	        name[i] = rs.getString("RestName");
	        i++;
            }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return name;
    }  
    
    public static String[] selectFilterCuisineAddress(String cuisine){
        String[] address = null; 
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Address from Restaurant "
                    + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                    + "INNER JOIN Cuisine_Types USING (Cuisine_ID)"
                    + "WHERE Cuisine = \"" + cuisine 
                    + "\"");
            
            int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            address = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
	        address[i] = rs.getString("Address");
	        i++;
            }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return address;
    }  
    public static String[] selectFilterCuisinePhone(String cuisine){
        String[] phone = null; 
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Telephone from Restaurant "
                    + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                    + "INNER JOIN Cuisine_Types USING (Cuisine_ID)"
                    + "WHERE Cuisine = \"" + cuisine 
                    + "\"");
            
            int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            phone = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
	        phone[i] = rs.getString("Telephone");
	        i++;
            }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return phone;
    }  
}