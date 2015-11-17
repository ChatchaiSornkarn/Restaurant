/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static restaurant.DBConnection.conn;

/**
 *
 * @author user
 */
public class SQLRegisterStore extends SQLStringReturn {
    
    public static void InsertUserRegister(String a, String b){
	try {
		PreparedStatement p;
                Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(30);
		int index = 0;
                ResultSet r = stmt.executeQuery("select * from Login ");
		      
//	       while(r.next())
//		      {index++;}
	    	  
	    p = conn.prepareStatement("INSERT INTO Login (User,Password) VALUES (?, ?)");
//	    p.setInt(1, index);
	    p.setString(1, a);
	    p.setString(2, b);
	      
	    p.executeUpdate();
	    p.close();
	    }catch (Exception e) {
			  e.printStackTrace();
		}     
        }
}
