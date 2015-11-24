/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static restaurant.DBConnection.conn;

/**
 *
 * @author chatchai
 */
public class SQLInsert extends SQLStringReturn{
    
    public static void addRestaurant(String a, String b, String c, String d, String f, String g, boolean h, String i){
        
        try {
	    PreparedStatement p = conn.prepareStatement("INSERT INTO Restaurant (RestName,Address,Telephone,Budget_ID,StudentDiscount) VALUES (?, ?, ?, 1,0)");
	    p.setString(1, a);
	    p.setString(2, b);
	    p.setString(3, c);  
            
	    p.executeUpdate();
	    p.close();
	    }
        catch (Exception e) {
			  e.printStackTrace();
		}     
        
        
    }
    
}
