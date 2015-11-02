/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.*;

/**
 *
 * @author chatchai
 */
public class SQLRestaurant extends DBConnection{
    
    /**
     * select id and name from Restaurant
     * @return name[] array
     */
    public static String[] selectResName() throws SQLException{
        String[] name = null;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Name from Restaurant ORDER BY Name");
            
            int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            name = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
                name[i] = rs.getString("Name");
                i++;
            }
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        return name;
    }
    
    /**
     * select cuisine from Restaurant
     * @return cuisine[] array
     */
    public static String[] selectCuisine(){
        String[] cuisine = null;
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from CUISINE");
            
            int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            cuisine = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
	        cuisine[i] = rs.getString("Name");
	        i++;
            }
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        return cuisine;
    }
    
}
