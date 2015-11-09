package restaurant;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import static restaurant.DBConnection.conn;

public class SQLSearch extends SQLFilter {

	public static String searchRestName(String restName){
        String name = ""; 
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select RestName  from Restaurant "
                    + "WHERE RestName = \"" + restName 
                    + "\"");
                    
                    
                    if (!rs.next()) {
                    return "wrong";
                    
                    }
                    else {
                    rs.beforeFirst();
                    rs.next();
        	    	name = rs.getString(1);
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return name;
    }  
	
	public static String searchRestAddress(String RestName){
        String address = ""; 
        
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Address from Restaurant "
                    + "WHERE RestName = \"" + RestName 
                    + "\"");

            if (!rs.next()) {
                return "wrong";
                
                }
                else {
                rs.beforeFirst();
                rs.next();
    	    	address = rs.getString(1);
                }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return address;
    }  
	public static String searchRestPhone(String RestName ){
        String phone = ""; 
        
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Telephone  from Restaurant "
                    + "WHERE RestName = \"" + RestName 
                    + "\"");

            if (!rs.next()) {
                return "wrong";
                
                }
                else {
                rs.beforeFirst();
                rs.next();
    	    	phone = rs.getString(1);
                }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return phone;
    }  
	
	public static ResultSet searchRestTest(String cuisine){
		ResultSet rs = null;
        
        try{
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("Select RestName, Address, Telephone from Restaurant "
                    + "INNER JOIN Restaurant_Cuisine_Types USING (Restaurant_ID) "
                    + "INNER JOIN Cuisine_Types USING (Cuisine_ID)"
                    + "WHERE Cuisine = \"" + cuisine 
                    + "\"");
//            rs = stmt.executeQuery("Select RestName  from Restaurant "
//                    + "WHERE RestName = \"" + restName 
//                    + "\"");
                    
                    
//                    if (!rs.next()) {
//                    rs = {"wrong"};
//                    return "wrong";
                    
//                    }
//                    else {
//                    rs.beforeFirst();
//                    rs.next();
//        	    	name = rs.getString(1);
////                    }
            
//            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return rs;
    }  
}
