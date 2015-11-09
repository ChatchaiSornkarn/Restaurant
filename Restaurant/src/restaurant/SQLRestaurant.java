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
public class SQLRestaurant extends SQLStringReturn{
    
    private static String[] name;
    private static String[] cuisine;
    
    /**
     * select id and name from Restaurant
     * @return name[] array
     * @throws java.sql.SQLException
     */
    public static String[] selectRestName(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select RestName from Restaurant ORDER BY RestName");
            
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
    
    /**
     * select cuisine from Restaurant
     * @return cuisine[] array
     */
    public static String[] selectCuisine(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Cuisine_Types");
            
            int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            cuisine = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
	        cuisine[i] = rs.getString("Cuisine");
	        i++;
            }
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        return cuisine;
    }
    
    public static String[] selectRestTel(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Telephone from Restaurant ORDER BY RestName");
            
            int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            name = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
                name[i] = rs.getString("Telephone");
                i++;
            }
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        return name;
    }
    
    public static String[] selectRestAddress(){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Address from Restaurant ORDER BY RestName");
            
            int rowcount = 0;
            
            if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
            }
            
            name = new String[rowcount];
            int i = 0;
            
	    while(rs.next()){
                name[i] = rs.getString("Address");
                i++;
            }
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        return name;
    }
    
}
