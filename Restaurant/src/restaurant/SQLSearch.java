package restaurant;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import static restaurant.DBConnection.conn;
import static restaurant.SQLStringReturn.makeList;

public class SQLSearch extends SQLFilter {
    
    private static String[] name;
    private static String[] address;
    private static String[] phone;

	public static String[] searchRestName(String restName){
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select RestName  from Restaurant "
                    + "WHERE RestName Like \"" + restName + "%"
                    + "\"");
                    
                    int rowcount = 0;
                    
                    if (!rs.next()) {
                    String[] wrong = new String[]{"wrong"};
                    return wrong;
                    }
                    
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
	
	public static String[] searchRestAddress(String RestName){

        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Address from Restaurant "
                    + "WHERE RestName LIKE \"" + RestName + "%"
                    + "\"");

             int rowcount = 0;
                    
                    if (!rs.next()) {
                    String[] wrong = new String[]{"wrong"};
                    return wrong;
                    }
                    
                    if (rs.last()) {
                    rowcount = rs.getRow();
                     rs.beforeFirst();
                    }
            
                    address = new String[rowcount];
                    int i = 0;
                    
                     while(rs.next()){
                    address[i] = rs.getString("address");
                    i++;
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return address;
    }  
	public static String[] searchRestPhone(String RestName ){ 
           
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Telephone  from Restaurant "
                    + "WHERE RestName LIKE \"" + RestName + "%"
                    + "\"");

            int rowcount = 0;
                    
                    if (!rs.next()) {
                    String[] wrong = new String[]{"wrong"};
                    return wrong;
                    }
                    
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
	
	public static String[][] SelectSearch(String restName){
        name = makeList("Select RestName from Restaurant "
                + "WHERE RestName LIKE \"" + restName + "%" + "\" ORDER BY RestName");
        
        address = makeList("Select Address from Restaurant "
                + "WHERE RestName LIKE \"" + restName + "%" +  "\" ORDER BY RestName");
        
        phone = makeList("Select Telephone from Restaurant "
                + "WHERE RestName LIKE \"" + restName + "%" +  "\" ORDER BY RestName");
        
        String[][] bugetRest = new String[name.length][3];
        
        for(int i = 0; i < name.length; i ++){
            bugetRest[i][0] = name[i];
            bugetRest[i][1] = address[i];
            bugetRest[i][2] = phone[i];
                }
        
        return bugetRest;
        
    }
}
