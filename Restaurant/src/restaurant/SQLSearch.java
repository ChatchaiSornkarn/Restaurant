package restaurant;

import java.awt.Image;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
//import restauran.DBConnection;
import restaurant.Connector;

public class SQLSearch extends Connector{
    
    private static String[] name;
    private static String[] address;
    private static String[] phone;
    private static String[] weblink;

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
        
       public static ImageIcon[] searchRestImage(String restName){
            ArrayList<ImageIcon> is = new ArrayList<ImageIcon>();
            ImageIcon[] im = null;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select image  from Restaurant "
                    + "WHERE RestName Like \"" + restName + "%"
                    + "\"");
                    
                    int rowcount = 0;
                
                    
                    if (!rs.next()) {
                    

                    }
                    
                    
                     while(rs.next()){
                      if(rs.getBytes(1) != null ){
                    byte[] imageBytes = rs.getBytes(1);
                    ImageIcon  icon = new ImageIcon(imageBytes);
                    Image image = icon.getImage();
                    Image bild= image.getScaledInstance(180, 100,  java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(bild);
                    is.add(icon);
                } else if (rs.getBytes(1)== null) {
                    ImageIcon  icon = new ImageIcon("\\resources\\FinalBack.png");
                    Image image = icon.getImage();
                    Image bild= image.getScaledInstance(180, 100,  java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(bild);
                    is.add(icon);

             
                    }  
                     }
            close(stmt);
        im = is.toArray(new ImageIcon[is.size()]);
        
        } catch(Exception e){
        System.err.println(e.getMessage());
        }
         return im; 
    }  
	
       public static String[] searchRestWebsite(String RestName ){ 
           
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select Website  from Restaurant "
                    + "WHERE Website LIKE \"" + RestName + "%"
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
            
                    weblink = new String[rowcount];
                    int i = 0;
                    
                     while(rs.next()){
                    weblink[i] = rs.getString("Website");
                    i++;
                    }
            
            close(stmt);
        }
        catch(Exception e){
        System.err.println(e.getMessage());
        }
        
        return weblink;
    }  
}
	

