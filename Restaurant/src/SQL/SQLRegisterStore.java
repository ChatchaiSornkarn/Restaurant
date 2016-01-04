/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static DBConnection.DBConnection.conn;

/**
 * Class: Register to become user
 * @author Chiara
 */
public class SQLRegisterStore extends SQLStringReturn {
    
    public static void InsertUserRegister(String a, String b){
	try {
		PreparedStatement p;
                Statement stmt = conn.createStatement();
                stmt.setQueryTimeout(30);
		int index = 0;
                ResultSet rs = stmt.executeQuery("select * from Login ");
	    	  
	    p = conn.prepareStatement("INSERT INTO Login (User,Password) VALUES (?, ?)");
	    p.setString(1, a);
	    p.setString(2, b);
	      
	    p.executeUpdate();
	    p.close();
	    }catch (Exception e) {
			  e.printStackTrace();
		}     
        }
}
