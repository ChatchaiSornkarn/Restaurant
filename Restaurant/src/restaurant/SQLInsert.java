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
 * @author 
 */
public class SQLInsert extends SQLStringReturn{
    
    
    public static void addRestaurant(String a, String b, String c, String d, String f, String g, boolean h, String i){
        
        try {
            FileInputStream image;
	    PreparedStatement p = conn.prepareStatement("INSERT INTO Restaurant (RestName,Address,Telephone,Website,image,Budget_ID,StudentDiscount) VALUES (?, ?, ?, ?, ?, ?, ?)");
	    p.setString(1, a);
	    p.setString(2, b);
	    p.setString(3, c);  
            p.setString(4, d);
            
            try {
                        image = new FileInputStream(new File(f));
                        p.setBlob(5, image);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SQLInsert.class.getName()).log(Level.SEVERE, null, ex);
                        p.setNull(5,java.sql.Types.BLOB);
                    }
            int budgetID = 0;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Budget_ID from Budget where BudgetRange = \"" + g + "\"");
            while(rs.next() ){
    	        budgetID = rs.getInt("Budget_ID");
	        }
            p.setInt(6, budgetID);
            
            if(h == true){
                p.setInt(7, 1);
            }else{
                p.setInt(7, 0);
            }

            
	    p.executeUpdate();
	    p.close();
	    }
        catch (Exception e) {
			  e.printStackTrace();
		}     
        
        
    }
    
}
